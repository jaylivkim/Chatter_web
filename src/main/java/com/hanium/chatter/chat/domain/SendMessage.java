package com.hanium.chatter.chat.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SendMessage {
	private String name;
	private String contents;
	private Date sendDate;
	private String roomName;
}
