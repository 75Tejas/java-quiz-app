package com.velocity.MiniProj;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		System.out.println("==================================");
		System.out.println("    Welcome to the Quiz System    ");
		System.out.println("==================================");

		while (!exit) {
			System.out.println("\nSelect Role:");
			System.out.println("1. Student");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			System.out.print("Enter your choice (1-3): ");

			int roleChoice = 0;
			try {
				roleChoice = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a number between 1 and 3.");
				continue;
			}

			switch (roleChoice) {
			case 1:
				studentMenu(scanner);
				break;
			case 2:
				if (Admin.login(scanner)) {
					adminMenu(scanner);
				} else {
					System.out.println("Admin login failed.");
				}
				break;
			case 3:
				System.out.println("Thank you for using the Quiz System. Goodbye!");
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}

		scanner.close();
	}

	// Student Menu
	public static void studentMenu(Scanner scanner) {
		boolean back = false;
		int loggedInStudentId = -1;
		while (!back) {
			System.out.println("\nStudent Menu:");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Display Questions");
			System.out.println("4. Take Quiz");
			System.out.println("5. Display Quiz Result");
			System.out.println("6. Back to Main Menu");
			System.out.print("Enter your choice (1-6): ");

			int choice = 0;
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number between 1 and 6.");
				continue;
			}

			switch (choice) {
			case 1:
				Student.register(scanner);
				break;
			case 2:
				loggedInStudentId = Student.login(scanner);
				break;
			case 3:
				Question.displayAllQuestions();
				break;
			case 4:
				if (loggedInStudentId != -1) {
                    Quiz.takeQuiz(scanner, loggedInStudentId);
                } else {
                    System.out.println("Please login first!");
                }
                break;
			case 5:
				//Result.storeQuizResult(scanner);
				break;
			case 6:
				back = true;
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}
	}

	// Admin Menu
	public static void adminMenu(Scanner scanner) {
		boolean back = false;
		while (!back) {
			System.out.println("\nAdmin Menu:");
			System.out.println("1. Display All Students' Scores");
			System.out.println("2. Fetch Student Score by ID");
			System.out.println("3. Add Question");
			System.out.println("4. Back to Main Menu");
			System.out.print("Enter your choice (1-4): ");

			int choice = 0;
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number between 1 and 4.");
				continue;
			}

			switch (choice) {
			case 1:
				Admin.displayAllScores(scanner);
				break;
			case 2:
				Admin.fetchScoreById(scanner);
				break;
			case 3:
				Admin.addQuestion(scanner);
				break;
			case 4:
				back = true;
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}
	}
}
