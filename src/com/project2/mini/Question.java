package com.project2.mini;

import java.sql.*;
import java.util.*;

public class Question {

	private int id;
	private String question;
	private String option1, option2, option3, option4, correct_answer;

	public Question(int id, String question, String option1, String option2, String option3, String option4,
			String correct_answer) {
		this.id = id;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correct_answer = correct_answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getOption1() {
		return option1;
	}

	public String getOption2() {
		return option2;
	}

	public String getOption3() {
		return option3;
	}

	public String getOption4() {
		return option4;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	// Add a question to the database
	public static boolean addQuestionToDB(String question, String option1, String option2, String option3,
			String option4, String correct_answer) {
		String sql = "INSERT INTO Question (question, option1, option2, option3, option4, correct_answer) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DbManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, question);
			pstmt.setString(2, option1);
			pstmt.setString(3, option2);
			pstmt.setString(4, option3);
			pstmt.setString(5, option4);
			pstmt.setString(6, correct_answer);
			int affected = pstmt.executeUpdate();
			return affected > 0;
		} catch (Exception e) {
			System.out.println("Error adding question: " + e.getMessage());
			return false;
		}
	}

	// Fetch all questions
	public static List<Question> fetchAllQuestions() {
		List<Question> questions = new ArrayList<>();
		String sql = "SELECT * FROM Question";
		try (Connection conn = DbManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Question q = new Question(rs.getInt("id"), rs.getString("question"), rs.getString("option1"),
						rs.getString("option2"), rs.getString("option3"), rs.getString("option4"),
						rs.getString("correct_answer"));
				questions.add(q);
			}
		} catch (Exception e) {
			System.out.println("Error fetching questions: " + e.getMessage());
		}
		return questions;
	}

	// Display all questions
	public static void displayAllQuestions() {
		List<Question> questions = fetchAllQuestions();
		if (questions.isEmpty()) {
			System.out.println("No questions found in the database.");
			return;
		}
		int count = 1;
		for (Question q : questions) {
			System.out.println("\nQuestion " + count + ":");
			System.out.println("Q: " + q.getQuestion());
			System.out.println("A. " + q.getOption1());
			System.out.println("B. " + q.getOption2());
			System.out.println("C. " + q.getOption3());
			System.out.println("D. " + q.getOption4());
			count++;
		}
	}

}
