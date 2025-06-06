package com.project2.mini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Result {
	// Save a student's quiz result
	public static void storeQuizResult(int studentId, int score, String grade) {
		try {
			Connection conn = DbManager.getConnection();
			String sql = "INSERT INTO Result (student_id, score, grade) VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, score);
			pstmt.setString(3, grade);
			pstmt.executeUpdate();
			System.out.println("Result saved!");
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Could not save result: " + e.getMessage());
		}
	}

	// Show a student's quiz result
	public static void displayQuizResult(int studentId) {
		try {
			Connection conn = DbManager.getConnection();
			String sql = "SELECT score, grade FROM Result WHERE student_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int score = rs.getInt("score");
				String grade = rs.getString("grade");
				System.out.println("Your Score: " + score + ", Grade: " + grade);
			} else {
				System.out.println("No result found for this student ID.");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Could not fetch result: " + e.getMessage());
		}
	}

	// Simple grade calculation
	public static String calculateGrade(int score, int totalQuestions) {
		double percent = (score * 100.0) / totalQuestions;
		if (percent >= 90)
			return "A";
		if (percent >= 75)
			return "B";
		if (percent >= 60)
			return "C";
		if (percent >= 40)
			return "D";
		return "F";
	}

}
