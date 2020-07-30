package com.example.dao;

import com.example.beans.Message;

public interface MessageDAO {
	
	public int createMessage(Message message, String context) throws Exception;
	
	public void connectMessageToAccount(int accountId, int messageId) throws Exception;

}
