String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일", "월", "화", "수", "목", "금", "토"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#chat").show();
    }
    else {
        $("#chat").hide();
    }
    $("#chat").html("");
}

// 소켓 연결
function connect() {
    var socket = new SockJS("/echo");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        // 입장에 대한 구독
        stompClient.subscribe('/chat/hello/' + $("#roomName").val(), function (message) {
            showHello(JSON.parse(message.body));
        });
        // 입장에 대한 메시지 전달
		stompClient.subscribe('/chat/detail/' + $("#roomName").val(), function(message) {
			showDetail(JSON.parse(message.body));
		});
		// 퇴장에 대한 구독
		stompClient.subscribe('/chat/bye/' + $("#roomName").val(), function(message) {
			showBye(JSON.parse(message.body));
		});
		sendHello();
    });
}

// 소켓 연결 끊음
function disconnect() {
    if (stompClient != null) {
    	sendBye();
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendHello() {
    stompClient.send("/hello", {}, JSON.stringify({
    	name	: $("#name").val(),
    	roomName: $("#roomName").val()
    }));
}

function sendDetail() {
    stompClient.send("/detail", {}, JSON.stringify({
    	name	: $("#name").val(),
    	contents: $("#btn-input").val(),
    	roomName: $("#roomName").val()
    }));
}

function sendBye() {
    stompClient.send("/bye", {}, JSON.stringify({
    	name	: $("#name").val(),
    	roomName: $("#roomName").val()
    }));
}

function showDetail(message) {
	var html = "";
	if(message.name == $('#name').val()){
		html += '<li class="left clearfix">';
		html += '	<div class="chat-body clearfix">';
		html += '		<div class="header">';
		html += '		<strong class="pull-right primary-font">' + message.name + '</strong>';
		html += '		<small class="text-muted">';
		html += '			<i class="fa fa-clock-o fa-fw"></i>';
		html += 			new Date(message.sendDate).format("MM/dd E a/p hh:mm");
		html += '		</small>';
		html += '	</div>';
		html += '	<p class="pull-right">';
		html += 	message.contents;
		html += '	</p>';
		html += '	</div>';
		html += '</li>';
	} else {
		html += '<li class="left clearfix">';
		html += '	<div class="chat-body clearfix">';
		html += '		<div class="header">';
		html += '		<strong class="primary-font">' + message.name + '</strong>';
		html += '		<small class="pull-right text-muted">';
		html += '			<i class="fa fa-clock-o fa-fw"></i>';
		html += 			new Date(message.sendDate).format("MM/dd E a/p hh:mm");
		html += '		</small>';
		html += '	</div>';
		html += '	<p>';
		html += 	message.contents;
		html += '	</p>';
		html += '	</div>';
		html += '</li>';
	}
	
	$(".chat").append(html);
	$('.panel-body').scrollTop($(".chat")[0].scrollHeight);
}

function showHello(message) {
	var html = "";
	
	html += '<li class="left clearfix">';
	html += '	<div class="chat-body clearfix">';
	html += '	<div class="header">';
	html += '		<p>';
	html += '			<strong class="primary-font">';
	html += 				message.name;
	html += '			</strong>';
	html += '			 님이 입장하였습니다.';
	html += '		</p>';
	html += '		<small class="pull-right text-muted">';
	html += '			<i class="fa fa-clock-o fa-fw"></i>';
	html += 			new Date(message.sendDate).format("MM/dd E a/p hh:mm");
	html += '		</small>';
	html += '	</div>';
	html += '	<p>';
	html += '	방제목 : ';
	html += 	message.roomName;
	html += '	</p>';
	html += '	</div>';
	html += '</li>';
	
	$(".chat").append(html);
	$('.panel-body').scrollTop($(".chat")[0].scrollHeight);
}

function showBye(message) {
	var html = "";
	
	html += '<li class="left clearfix">';
	html += '	<div class="chat-body clearfix">';
	html += '	<div class="header">';
	html += '		<p>';
	html += '			<strong class="primary-font">';
	html += 				message.name;
	html += '			</strong>';
	html += '			 님이 퇴장하였습니다.';
	html += '		</p>';
	html += '		<small class="pull-right text-muted">';
	html += '			<i class="fa fa-clock-o fa-fw"></i>';
	html += 			new Date(message.sendDate).format("MM/dd E a/p hh:mm");
	html += '		</small>';
	html += '	</div>';
	html += '	</div>';
	html += '</li>';
	
	$(".chat").append(html);
	$('.panel-body').scrollTop($(".chat")[0].scrollHeight);
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		// 소켓 연결
		connect();
	});
	$("#disconnect").click(function() {
		// 소켓 연결 끊음
		disconnect();
	});
	$("#btn-chat").click(function() {
		// 메시지 전달
		sendDetail();
		$('#btn-input').val('');
	});
});

