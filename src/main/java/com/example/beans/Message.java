package com.example.beans;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class Message {
	
	private String msg;

	public Message(String msg) {
		super();
		this.msg = msg;
	}

	public Message() {
		super();
	}

}
