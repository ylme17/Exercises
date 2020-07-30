package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMethods {

	private static final String SERVER_URL = "jdbc:postgresql://localhost:5433/";
	private static final String DB_URL = "jdbc:postgresql://localhost:5433/exercises";
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";
	
	public static void createDb() throws SQLException, ClassNotFoundException {		
		Connection con = null;
		try {

			Class.forName(DRIVER);
			System.out.println("driver loaded");
			con = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
			Statement st = con.createStatement();
			st.executeUpdate("CREATE DATABASE Exercises");
			System.out.println("db created");
		} catch (SQLException e) {
			System.out.println("db not created - " + e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void createTables() {		
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			Statement stmt = con.createStatement();

			String AccountTable = "CREATE TABLE account(id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, account_name VARCHAR(255))";
			stmt.execute(AccountTable);

			String MessageTable = "CREATE TABLE message(id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, message_content VARCHAR(255))";
			stmt.execute(MessageTable);

			String AccountMessageTable = "CREATE TABLE account_message(account_id INT, message_id INT, "
					+ "PRIMARY KEY(account_id, message_id))";
			stmt.execute(AccountMessageTable);

			System.out.println("tables created");
		} catch (SQLException e) {
			System.out.println("tables not created - " + e.getMessage());
		}
	}
	
	public static void addColumn(String table, String name, String type) {
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			Statement st = con.createStatement();

			String addColumnSql = "ALTER TABLE " + table + " ADD COLUMN " + name +" " + type + "";
			st.execute(addColumnSql);

			st.close();
			System.out.println("column added");
		} catch (SQLException e) {
			System.out.println("column not added - " + e.getMessage());
		}
	}
	
	public static void dropAllTables() {		
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			Statement st = con.createStatement();

			String dropAccountTableSql = "DROP TABLE account";
			st.execute(dropAccountTableSql);

			String dropMessageTableSql = "DROP TABLE message";
			st.execute(dropMessageTableSql);

			String dropAccountMessageTableSql = "DROP TABLE account_message";
			st.execute(dropAccountMessageTableSql);

			st.close();
			System.out.println("tables deleted");
		} catch (SQLException e) {
			System.out.println("tables not deleted - " + e.getMessage());
		}

	}

}
