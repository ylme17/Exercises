package com.example.dao;

import java.util.Collection;

import com.example.beans.Account;
import com.example.beans.Message;

public interface AccountDAO {
	
	public void createAccount(Account account) throws Exception;
	
	public Collection<Account> getAllAccounts() throws Exception;
	
	public Collection<Message> getMessages(int accountId, String context) throws Exception;
	
//	public boolean checkIfExist(Account account) throws Exception;

}
