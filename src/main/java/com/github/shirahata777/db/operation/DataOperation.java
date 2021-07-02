package com.github.shirahata777.db.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

public class DataOperation {

	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/form";
	private static final String USER = "shirahata";
	private static final String PASS = "qwertyuiop";

	public static void insertFromData(String name, String email, String content)  {
		
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis() - 1000*60*60*24);
		

		try (Connection connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS)) {
			try {
				Class.forName(POSTGRES_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			String sql = "INSERT INTO contact(name, email, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);

			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, content);
			st.setTimestamp(4, timestamp);
			st.setTimestamp(5, timestamp);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllFromData() {
		try (Connection connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS)) {
			try {
				Class.forName(POSTGRES_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM contact";
			ResultSet rset = stmt.executeQuery(sql);
			while (rset.next()) {
			    System.out.println(rset.getString(1));
			}
			rset.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
