package com.beatflux.db;

import java.sql.*;

public class ConnectionManager {

	private static final String URL = "jdbc:mysql://127.0.0.1/beatflux";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static Connection connection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return DriverManager.getConnection(URL, USER, PASS);
	}
}//end class


