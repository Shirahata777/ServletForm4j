package com.github.shirahata777.db.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class DataOperation {

	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/form";
	private static final String USER = "shirahata";
	private static final String PASS = "qwertyuiop";

	public static void insertFromData(String name, String email, String content) {

		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis() - 1000 * 60 * 60 * 24);

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

	public static ArrayList<ArrayList<String>> getAllFromData() {

		String sql = "SELECT * FROM contact";
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<ArrayList<String>> formDataList = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS)) {
			try {
				Class.forName(POSTGRES_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				ArrayList<String> formData = new ArrayList<>();
				formData.add(String.format("%d", rs.getInt("id")));
				formData.add(rs.getString("name"));
				formData.add(rs.getString("email"));
				formData.add(rs.getString("content"));
				formDataList.add(formData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}

		}

		return formDataList;
	}

}
