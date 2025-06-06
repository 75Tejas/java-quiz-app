package com.velocity.task1;


import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Take input from user
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();

		System.out.print("Enter your Date of Birth (YYYY-MM-DD): ");
		LocalDate dob = LocalDate.parse(scanner.nextLine());

		System.out.print("Enter Loan Amount: ");
		double loanAmount = scanner.nextDouble();

		System.out.print("Enter Loan Tenure (2 to 4): ");
		int tenure = scanner.nextInt();

		Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream("src/db.properties")) {
			// Load properties file
			props.load(fis);

			// Read properties
			String url = props.getProperty("db.url");
			String user = props.getProperty("db.username");
			String password = props.getProperty("db.password");

			// Load MySQL driver (optional for newer versions)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Create connection
			Connection conn = DriverManager.getConnection(url, user, password);

			CallableStatement stmt = conn.prepareCall("{CALL get_multiplier(?, ?, ?)}");
            stmt.setDate(1, Date.valueOf(dob));  // LocalDate to java.sql.Date
            stmt.setInt(2, tenure);
            stmt.registerOutParameter(3, Types.DECIMAL);
			
            stmt.execute();

            double multiplier = stmt.getDouble(3);
            
            if (multiplier == 0.0) {
                System.out.println("No multiplier found for the given age and tenure.");
            } else {
                double insurancePremium = (loanAmount / 1000.0) * multiplier;
                System.out.println("Dear " + name + ", your insurance premium is: ₹" + insurancePremium);
            }

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}