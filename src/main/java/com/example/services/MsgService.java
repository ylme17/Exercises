package com.example.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Message;

@RestController
@RequestMapping("/api")
public class MsgService {
	
	@GetMapping(value = "/get-msgs/{accountid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Message> message(@PathVariable("accountid") int id)
	{
		Message hello = new Message("Hello Yechiel");
		Message newEmails = new Message("You have new emails");
		
		ArrayList<Message> msgs = new ArrayList<>();		
		msgs.add(hello);
		msgs.add(newEmails);
		
		return msgs;
	}

}
