package com.github.shirahata777.db.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.github.shirahata777.model.FormDataQuery;

public class DataOperation {

	public static int insertFromData(FormDataQuery formQuery) {

		String sql = "INSERT INTO contact(name, email, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

		DataSource dataSource = null;

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/default");
		} catch (NamingException e) {
			e.printStackTrace();
			return 500;
		}

		try (Connection connection = dataSource.getConnection()) {

			try (PreparedStatement st = connection.prepareStatement(sql);) {
				st.setString(1, formQuery.getName());
				st.setString(2, formQuery.getEmail());
				st.setString(3, formQuery.getContent());
				st.setTimestamp(4, formQuery.getCreatedAt());
				st.setTimestamp(5, formQuery.getUpdatedAt());

				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 500;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 500;
		}
		return 200;
	}

	public static List<List<String>> getAllFromData() {

		String sql = "SELECT * FROM contact";
		PreparedStatement ps = null;
		ResultSet rs = null;

		DataSource dataSource = null;

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/default");
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}

		List<List<String>> formDataList = new ArrayList<>();

		try (Connection connection = dataSource.getConnection()) {

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
			return null;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}

		}

		return formDataList;
	}

	public static void initCreateFromDB() {

		String sql = "CREATE TABLE if not exists contact (\n" + "id SERIAL NOT NULL,\n"
				+ "name VARCHAR(255) NOT NULL, \n" + "email VARCHAR(255) NOT NULL,\n"
				+ "content VARCHAR(255) NOT NULL,\n" + "created_at TIMESTAMP,\n" + "updated_at TIMESTAMP,\n"
				+ "PRIMARY KEY (id)\n" + "); PRIMARY KEY (SingerId)\n";

		DataSource dataSource = null;

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/default");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		try (Connection connection = dataSource.getConnection()) {
			try (Statement st = connection.createStatement()) {
				st.execute(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
