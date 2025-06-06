package com.project2.mini;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbManager {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
			Properties pros = new Properties();

			try (InputStream input = new FileInputStream("src/db.properties")) {
				pros.load(input);

				String url = pros.getProperty("db.url");
				String user = pros.getProperty("db.user");
				String password = pros.getProperty("db.password");

				// Load MySQL driver (optional for newer versions)
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Create connection
				Connection conn = DriverManager.getConnection(url, user, password);
				// Create Table
				createTable(conn);
				createQuestionTable(conn);

				createResultTable(conn);

				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
			Properties pros = new Properties();
			try (InputStream input = new FileInputStream("src/db.properties")) {
				pros.load(input);

				String url = pros.getProperty("db.url");
				String user = pros.getProperty("db.user");
				String password = pros.getProperty("db.password");

				// Load MySQL driver (optional for newer versions)
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Create and return connection
				return DriverManager.getConnection(url, user, password);
			}
		}

		public static void createTable(Connection connection) throws SQLException {

			String createTable = "CREATE TABLE IF NOT EXISTS Student (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "first_name VARCHAR(50)," + "last_name VARCHAR(50)," + "username VARCHAR(50) UNIQUE,"
					+ "password VARCHAR(50)," + "city VARCHAR(50)," + "email VARCHAR(100)," + "mobile VARCHAR(15)" + ");";

			try (Statement stmt = connection.createStatement()) {
				stmt.execute(createTable);
				System.out.println("Table created");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static void createQuestionTable(Connection connection) {

			String createTable = "CREATE TABLE IF NOT EXISTS Question (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "question VARCHAR(255)," + "option1 VARCHAR(100)," + "option2 VARCHAR(100)," + "option3 VARCHAR(100),"
					+ "option4 VARCHAR(100)," + "correct_answer VARCHAR(100)" + ");";

			try (Statement stmt = connection.createStatement()) {
				stmt.execute(createTable);
				System.out.println("Table created");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static void createResultTable(Connection connection) {
			String createTable = "CREATE TABLE IF NOT EXISTS Result (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "student_id INT," + "score INT," + "grade VARCHAR(5),"
					+ "FOREIGN KEY (student_id) REFERENCES Student(id)" + ");";

			try (Statement stmt = connection.createStatement()) {
				stmt.execute(createTable);
				System.out.println("Table created");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}


	}


