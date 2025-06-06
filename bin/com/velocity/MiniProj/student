package com.velocity.MiniProj;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Student {
	public static void register(Scanner scanner) {
		System.out.println("\n--- Student Registration ---");
		System.out.print("First Name: ");
		String firstName = scanner.nextLine();
		System.out.print("Last Name: ");
		String lastName = scanner.nextLine();
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		System.out.print("City: ");
		String city = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Mobile: ");
		String mobileStr = scanner.nextLine();

		long mobile = 0;
		try {
			mobile = Long.parseLong(mobileStr);
		} catch (NumberFormatException e) {
			System.out.println("Invalid mobile number. Registration failed.");
			return;
		}

		Properties pros = new Properties();
		try (InputStream input = new FileInputStream("src/db.properties")) {
			pros.load(input);

			String url = pros.getProperty("db.url");
			String user = pros.getProperty("db.user");
			String password1 = pros.getProperty("db.password");

			// Load MySQL driver (optional for newer versions)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Create connection
			Connection conn = DriverManager.getConnection(url, user, password1);

			// insert into table Student

			Student.insertdata(firstName, lastName, username, password, city, email, mobile, conn);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void insertdata(String firstname, String lastname, String username, String password, String city,
			String email, long mobile, Connection conn) {

		String sql = "INSERT INTO student (first_name, last_name, username, password, city, email, mobile) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, city);
			ps.setString(6, email);
			ps.setLong(7, mobile);

			int exec = ps.executeUpdate();
			System.out.println("Insert is Done:" + exec);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int login(Scanner scanner) {

		System.out.println("\n--- Student Login ---");
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		Properties pros = new Properties();
		try (InputStream input = new FileInputStream("src/db.properties")) {
			pros.load(input);

			String url = pros.getProperty("db.url");
			String user = pros.getProperty("db.user");
			String dbPassword = pros.getProperty("db.password");

			// Load MySQL driver (optional for newer versions)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Create connection
			try (Connection conn = DriverManager.getConnection(url, user, dbPassword)) {
				String sql = "SELECT id, first_name FROM student WHERE username = ? AND password = ?";
				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1, username);
					ps.setString(2, password);

					try (java.sql.ResultSet rs = ps.executeQuery()) {
						if (rs.next()) {
							int studentId = rs.getInt("id");
							System.out.println("Login successful! Welcome, " + rs.getString("first_name") + ".");
							return studentId; 
						} else {
							System.out.println("Invalid username or password. Login failed.");
							return -1;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		

	}

}
