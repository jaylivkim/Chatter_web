Chatter Project
===============
***

Chatter Project 중 대화 내용 전달을 위한 웹 채팅 서버
-----------------------------------------------------

## 개요
- WebSocket + SockJS + STOMP를 활용하여 웹 채팅 구현
- Spring Boot를 이용하여 Back-End 구축
- 개발 진행 상황

## WebSocket + SockJS + STOMP 채택
1. WebSocket
- 웹소켓이란 HTTP의 단점인 단방향 통신을 개선하기 위한 표준. 즉, 양방향 통신이 가능.
- 방화벽이 있는 환경에서도 사용이 가능하고 Client와 Server가 실시간으로 자유로운 통신이 가능.
- HTML5에서 채택된 최신 프로토콜인 관계로 IE9 미만의 오래된 브라우저에서 지원이 안되는 단점.

2. SockJS
- Spring.io에서 guide를 제공하는 라이브러리로써 WebSocket이 지원하지 못하는 브라우저들도 지원이 가능한 장점.
- 또한 WebSocket의 모든 기능을 그대로 사용이 가능한 동시에 웹 스키마를 ws:// 대신 http:// 사용이 가능함.
- Client가 메시지를 주고 받는 모든 과정을 Hanlding 해야 하는 단점.

3. STOMP
- Simple/Streaming Text Oriented Messaging Protocol. 말 그대로 간단한 텍스트 기반 메시지 프로토콜.
- http가 아닌 TCP 환경에서도 통신을 가능하게 해주는 장점.
- 메시지를 주고 받는 과정을 subscribe 기능을 통해 Handling 과정을 단축시켜 간단히 구현 가능한 장점.

>WebSocket 프로토콜을 채택하고 오래된 브라우저까지 지원하기 위해 SockJS, Handling 과정의 단순화를 위해 STOMP를 채택.
>결과적으로 IE, Chrome, Android, iOS 등 대부분의 환경에서 정상적으로 잘 작동함을 확인.

## 서버 구축
- Spring Boot를 이용하여 설정에 들어가는 시간과 노력을 줄이고 개발에 집중.
- 기존의 Spring.io에서 제공하는 WebSocket Guide를 참고하여 프로젝트에 적합한 구조로 수정하는 과정을 거침.

- ChatController에서 채팅방 이름에 따른 구독 처리를 위한 부분 수정.
    - @SendTo("/chat/hello")

    - messagingTemplate.convertAndSend("/chat/hello/" + message.getRoomName(), message);
- convertAndSend를 구현하기 위해 SimpMessagingTemplate 사용.

- 채팅기능 구현을 위한 chat.js 재구성


## 개발 진행 상황
- 17.08.26 진행상황 update 1.Spring Security를 활용해서 간단한 로그인 & 회원가입 기능 구현.
- 17.08.26 진행상황 update 2.Maven -> Gradle로 변환 완료.
- 프로젝트의 프로토타입을 위한 최소한의 기능들을 구현 완료한 상태.
