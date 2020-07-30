package com.example.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.example.beans.Account;
import com.example.beans.Message;
import com.example.connection.ConnectionPool;
import com.example.dao.AccountDAO;

public class AccountDAODB implements AccountDAO {
	
	private ConnectionPool connectionPool;

	@Override
	public void createAccount(Account account) throws Exception {
		Connection con = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			con = connectionPool.getConnection();
			String createAccountSql = "INSERT INTO account (account_name) VALUES('" + account.getName() + "')";
			PreparedStatement pst = con.prepareStatement(createAccountSql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			account.setId(rs.getInt(1));
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new Exception("cannot create new account[name: " + account.getName() + "]");
		} finally {
			if (con != null)
				connectionPool.returnConnection(con);
		}
	}

	@Override
	public Collection<Account> getAllAccounts() throws Exception {
		Connection con = null;
		Collection<Account> accounts = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			con = connectionPool.getConnection();
			String getAllAccountsSql = "SELECT * FROM account";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(getAllAccountsSql);
			accounts = new ArrayList<Account>();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setName(rs.getString("account_name"));
				accounts.add(account);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (con != null)
				connectionPool.returnConnection(con);
		}
		return accounts;
	}

	@Override
	public Collection<Message> getMessages(int accountId, String context) throws Exception {
		Connection con = null;
		ArrayList<Message> messages = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			con = connectionPool.getConnection();
			messages = new ArrayList<Message>();
			String getMessagesSql = "SELECT * FROM message INNER JOIN account_message ON id=account_message.message_id "
					+ "WHERE account_message.account_id=" + accountId
							+ " AND message.context='" + context + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(getMessagesSql);
			while (rs.next()) {
				Message message = new Message();
				message.setMsg(rs.getString("message_content"));
				messages.add(message);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (con != null)
				connectionPool.returnConnection(con);
		}
		return messages;
	}
	
	public boolean checkIfExist(Account account) throws Exception {
		Connection con = null;
		boolean exist = false;
		try {
			connectionPool = ConnectionPool.getInstance();
			con = connectionPool.getConnection();
			String checkSql = "SELECT * FROM account WHERE account_name='" + account.getName() + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(checkSql);
			if (rs.next()) {
				exist = true;
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (con != null)
				connectionPool.returnConnection(con);
		}
		return exist;
	}
}
