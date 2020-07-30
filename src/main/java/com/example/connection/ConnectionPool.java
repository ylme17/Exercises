package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConnectionPool {

	private Set<Connection> connections = new HashSet<>();
	private static final int POOL_SIZE = 5;
	private static final String URL = "jdbc:postgresql://localhost:5433/Exercises";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";
	private static ConnectionPool instance;
	private boolean shutDown = false;

	private ConnectionPool() throws SQLException {
			for (int i = 0; i < POOL_SIZE; i++) {
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				connections.add(con);
			}
	}

	public synchronized static ConnectionPool getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public synchronized Connection getConnection() throws InterruptedException  {
		if (!shutDown) {
			while (connections.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					throw new InterruptedException();
				}
			}
			Iterator<Connection> it = connections.iterator();
			Connection con = it.next();
			it.remove();
			return con;
		} else {
			throw new InterruptedException("cannot get connection, connection pool closed");
		}
	}

	public synchronized void returnConnection(Connection con) {
		connections.add(con);
		notifyAll();
	}

}
