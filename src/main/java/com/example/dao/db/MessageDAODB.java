package com.example.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.beans.Message;
import com.example.connection.ConnectionPool;
import com.example.dao.MessageDAO;

public class MessageDAODB implements MessageDAO {
	
	private ConnectionPool connectionPool;

	@Override
	public int createMessage(Message message) throws Exception {
		Connection con = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			con = connectionPool.getConnection();
			String createMessageSql = "INSERT INTO message (message_content) VALUES('" + message.getMsg() + "')";
			PreparedStatement pst = con.prepareStatement(createMessageSql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			int messageId = rs.getInt(1);
			rs.close();
			pst.close();
			return messageId;
		} catch (Exception e) {
			throw new Exception("cannot create a new message[title: " + message.getMsg() + "]");
		} finally {
			if (con != null)
				connectionPool.returnConnection(con);
		}

	}
	
	@Override
	public void connectMessageToAccount(int accountId, int messageId) throws Exception {
		Connection con = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			con = connectionPool.getConnection();
			String insertMessageSql = "INSERT INTO account_message (account_id, message_id) VALUES("
					+ accountId + ", " + messageId + ")";
			Statement st = con.createStatement();
			st.execute(insertMessageSql);
			st.close();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			if (con != null)
				connectionPool.returnConnection(con);
		}

	}


}
