package com.velocity.MiniProj;

import java.util.List;
import java.util.Scanner;

public class Quiz {
	// Simple method to run a quiz for a student
	public static void takeQuiz(Scanner scanner, int studentId) {
		// Fetch all questions from database
		List<Question> questions = Question.fetchAllQuestions();

		if (questions.isEmpty()) {
			System.out.println("No questions available for the quiz.");
			return;
		}

		int totalQuestions = Math.min(5, questions.size()); // Ask 5 questions or less if not enough
		int score = 0;

		System.out.println("\n--- Quiz Started ---");
		for (int i = 0; i < totalQuestions; i++) {
			Question q = questions.get(i);
			System.out.println("\nQuestion " + (i + 1) + ":");
			System.out.println(q.getQuestionText());
			System.out.println("A. " + q.getOptionA());
			System.out.println("B. " + q.getOptionB());
			System.out.println("C. " + q.getOptionC());
			System.out.println("D. " + q.getOptionD());

			String answer = "";
			boolean valid = false;
			while (!valid) {
				System.out.print("Your answer (A/B/C/D): ");
				answer = scanner.nextLine().trim().toUpperCase();
				if (answer.matches("[ABCD]")) {
					valid = true;
				} else {
					System.out.println("Invalid input. Please enter A, B, C, or D.");
				}
			}

			if (answer.equals(q.getCorrectOption().toUpperCase())) {
				score++;
			}
		}

		System.out.println("\nQuiz completed!");
		System.out.println("Your Score: " + score + " out of " + totalQuestions);
		
		
		String grade = Result.calculateGrade(score, totalQuestions);
	    Result.storeQuizResult(studentId, score, grade);

		
	}

}
