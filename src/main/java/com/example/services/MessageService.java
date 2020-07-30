package com.example.services;

import java.util.ArrayList;
import java.util.Collection;

import com.example.beans.Account;
import com.example.beans.Message;
import com.example.dao.AccountDAO;
import com.example.dao.MessageDAO;
import com.example.dao.db.AccountDAODB;
import com.example.dao.db.MessageDAODB;

public class MessageService {

	private AccountDAO accountDAO = new AccountDAODB();
	private MessageDAO messageDAO = new MessageDAODB();
	
	public void createAccount(Account account) throws Exception {
		if (accountDAO.checkIfExist(account) == false) {
			accountDAO.createAccount(account);
		} else {
			throw new Exception(account.getName() + " already exist");
		}
	}
	
	public Collection<Account> getAllAccounts() throws Exception {
		Collection<Account> allAccounts = new ArrayList<>();
		allAccounts = accountDAO.getAllAccounts();
		return allAccounts;
	}
	
	public void createMessage(Message messgae, int accountId, String context) throws Exception {
		int messageId = messageDAO.createMessage(messgae, context);
		messageDAO.connectMessageToAccount(accountId, messageId);
	}
	
	public Collection<Message> getMessages(int accountId, String context) throws Exception {
		Collection<Message> allMessages = accountDAO.getMessages(accountId, context);
		return allMessages;
	}

}
