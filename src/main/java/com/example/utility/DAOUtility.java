package com.example.utility;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtility {
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "password";
	private static final String URL = "jdbc:postgresql://localhost:5432/bank_database";
	private static Connection connection;
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return connection;
	}

}
