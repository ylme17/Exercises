package com.example.controllers;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
