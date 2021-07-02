package com.github.shirahata777.servlet.db.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataOperation {

	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/form";
	private static final String USER = "shirahata";
	private static final String PASS = "qwertyuiop";

	public static void insertFromData(String name, String email, String content)  {
		

		try (Connection connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS)) {
			try {
				Class.forName(POSTGRES_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			String sql = "INSERT INTO contact(name, email, content) VALUES (?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);

			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, content);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
