package com.velocity.MiniProj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Question {

	// store question test,options

	private int id;
	private String questionText;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctOption;

	// when loading a question from the database and you know its ID
	public Question(int id, String questionText, String optionA, String optionB, String optionC, String optionD,
			String correctOption) {

		this.id = id;
		this.questionText = questionText;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correctOption = correctOption;
	}

	// getter
	public int getId() {
		return id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public String getOptionA() {
		return optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public Question(String questionText, String optionA, String optionB, String optionC, String optionD,
			String correctOption) {
		this(0, questionText, optionA, optionB, optionC, optionD, correctOption);
	}

	public static boolean addQuestionToDB(String questionText, String optionA, String optionB, String optionC, String optionD, String correctOption) {
		String sql = "INSERT INTO Question (question, optionA, optionB, optionC, optionD, correctOption) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DbManager.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, questionText);
			pstmt.setString(2, optionA);
			pstmt.setString(3, optionB);
			pstmt.setString(4, optionC);
			pstmt.setString(5, optionD);
			pstmt.setString(6, correctOption);
			int affected = pstmt.executeUpdate();
			return affected > 0;
		} catch (Exception e) {
			System.out.println("Error adding question: " + e.getMessage());
			return false;
		}
	}

	
	 public static List<Question> fetchAllQuestions() {
	        List<Question> questions = new ArrayList<>();
	        String sql = "SELECT * FROM Question";
	        try (Connection conn = DbManager.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                Question q = new Question(
	                    rs.getInt("id"),
	                    rs.getString("question"),
	                    rs.getString("optionA"),
	                    rs.getString("optionB"),
	                    rs.getString("optionC"),
	                    rs.getString("optionD"),
	                    rs.getString("correctOption")
	                );
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
	            System.out.println("Q: " + q.getQuestionText());
	            System.out.println("A. " + q.getOptionA());
	            System.out.println("B. " + q.getOptionB());
	            System.out.println("C. " + q.getOptionC());
	            System.out.println("D. " + q.getOptionD());
	            count++;
	        }
	    }

}
