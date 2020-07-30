package com.example.controllers;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Account;
import com.example.beans.Message;
import com.example.services.MessageService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class MessageController {
	
	private MessageService messageService = new MessageService();
	
	@GetMapping(value = "/get-msgs/{accountid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Message> getMessages(@PathVariable("accountid") int accountId) throws Exception {
		Collection<Message> msgs = messageService.getMessages(accountId);
		return msgs;
	}
	
	@PostMapping(value = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createAccount(@RequestBody Account account) throws Exception {
		messageService.createAccount(account);
	}
	
	@PostMapping(value = "/message/{accountid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createMessage(@RequestBody Message message, @PathVariable("accountid") int accountId) throws Exception {
		messageService.createMessage(message, accountId);
	}
	
	@GetMapping(value = "/getaccounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Account> getAccounts() throws Exception{
		Collection<Account> accounts = messageService.getAllAccounts();
		return accounts;
	}
	
}
