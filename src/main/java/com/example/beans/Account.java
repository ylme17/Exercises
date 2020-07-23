package com.example.beans;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public @Getter @Setter class Account {
	
	private int id;
	private String name;
	
	public Account(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Account() {
		super();
	}
	
}
