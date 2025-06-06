package com.velocity.MiniProj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {

	private static final String admin_username = "admin";
	private static final String admin_password = "admin123";

	public static boolean login(Scanner scanner) {
		System.out.print("Enter Admin Username: ");
		String username = scanner.nextLine();
		System.out.print("Enter Admin Password: ");
		String password = scanner.nextLine();

		if (username.equals(admin_username) && password.equals(admin_password)) {
			System.out.println("Admin login successful!");
			return true;
		} else {
			System.out.println("Invalid admin credentials.");
			return false;
		}
	}

	public static void displayAllScores(Scanner scanner) {

		try (Connection conn = DbManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT s.id, s.username, r.score FROM Student s JOIN Result r ON s.id = r.student_id")) {
			System.out.println("\nAll Scores:");
			System.out.println("ID\tUsername\tScore");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("username") + "\t" + rs.getInt("score"));
			}
		} catch (Exception e) {
			System.out.println("Error fetching scores: " + e.getMessage());
		}
		// TODO Auto-generated method stub

	}

	public static void fetchScoreById(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.print("Enter Student ID: ");
		int studentId = Integer.parseInt(scanner.nextLine());

		try (Connection conn = DbManager.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("SELECT score, grade FROM Result WHERE student_id = ?")) {
			pstmt.setInt(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("Score: " + rs.getInt("score") + ", Grade: " + rs.getString("grade"));
			} else {
				System.out.println("No result found for ID: " + studentId);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	public static void addQuestion(Scanner scanner) {
		System.out.println("Enter the question ");
		String question = scanner.nextLine();

		System.out.println("Enter option A:");
		String optionA = scanner.nextLine();
		System.out.println("Enter option B:");
		String optionB = scanner.nextLine();
		System.out.println("Enter option C:");
		String optionC = scanner.nextLine();
		System.out.println("Enter option D:");
		String optionD = scanner.nextLine();

		System.out.println("Enter the correct option (A/B/C/D):");

		// Reads the input and remove white spaces and covert this trimmed string to
		// Uppercase
		String correctOption = scanner.nextLine().trim().toUpperCase();

		// valid'n
		if (!correctOption.matches("[ABCD]")) {
			System.out.println("Invalid!!!! option");
			return;
		}
		boolean result = Question.addQuestionToDB(question, optionA, optionB, optionC, optionD, correctOption);
		if (result) {
			System.out.println("Question added successfully!");
		} else {
			System.out.println("Failed to add question.");
		}

	}

}
