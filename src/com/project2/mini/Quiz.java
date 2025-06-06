package com.project2.mini;

import java.util.List;
import java.util.Scanner;

public class Quiz {

	// Run a quiz for a student
	public static void takeQuiz(Scanner scanner, int studentId) {
		List<Question> questions = Question.fetchAllQuestions();
		if (questions.isEmpty()) {
			System.out.println("No questions available for the quiz.");
			return;
		}
		int totalQuestions = Math.min(5, questions.size());
		int score = 0;
		System.out.println("\n--- Quiz Started ---");
		for (int i = 0; i < totalQuestions; i++) {
			Question q = questions.get(i);
			System.out.println("\nQuestion " + (i + 1) + ":");
			System.out.println(q.getQuestion());
			System.out.println("A. " + q.getOption1());
			System.out.println("B. " + q.getOption2());
			System.out.println("C. " + q.getOption3());
			System.out.println("D. " + q.getOption4());

			String answer = "";
			while (true) {
				System.out.print("Your answer (A/B/C/D): ");
				answer = scanner.nextLine().trim().toUpperCase();
				if (answer.matches("[ABCD]"))
					break;
				System.out.println("Invalid input. Please enter A, B, C, or D.");
			}
			if (answer.equals(q.getCorrect_answer().toUpperCase())) {
				score++;
			}
		}
		System.out.println("\nQuiz completed!");
		System.out.println("Your Score: " + score + " out of " + totalQuestions);
		String grade = Result.calculateGrade(score, totalQuestions);
		Result.storeQuizResult(studentId, score, grade);
	}

}
