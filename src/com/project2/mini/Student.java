package com.project2.mini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student {
	// Register a new student
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
		
		if (mobileStr.length() != 10) {
		    System.out.println("Mobile number must be 10 digits. Registration failed.");
		    return;
		}

		long mobile = 0;
		try {
			mobile = Long.parseLong(mobileStr);
		} catch (NumberFormatException e) {
			System.out.println("Invalid mobile number. Registration failed.");
			return;
		}

		try {
			Connection conn = DbManager.getConnection();
			String sql = "INSERT INTO student (first_name, last_name, username, password, city, email, mobile) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, city);
			ps.setString(6, email);
			ps.setLong(7, mobile);
			int exec = ps.executeUpdate();
			if (exec > 0) {
				System.out.println("Registration successful!");
			} else {
				System.out.println("Registration failed.");
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Student login, returns student ID or -1
	public static int login(Scanner scanner) {
		System.out.println("\n--- Student Login ---");
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		try {
			Connection conn = DbManager.getConnection();
			String sql = "SELECT id, first_name FROM student WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int studentId = rs.getInt("id");
				System.out.println("Login successful! Welcome, " + rs.getString("first_name") + ".");
				rs.close();
				ps.close();
				conn.close();
				return studentId;
			} else {
				System.out.println("Invalid username or password. Login failed.");
				rs.close();
				ps.close();
				conn.close();
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return -1;
		}
	}

}
