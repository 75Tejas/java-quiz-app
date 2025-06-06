package com.velocity.task1;




import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Properties pros = new Properties();
		
		try(InputStream input = new FileInputStream("src/db.properties"))
		{
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
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public static void createTable(Connection connection) throws SQLException
	{
		
				
				 String createTable = "CREATE TABLE STUDENT("
				         + "ID INT NOT NULL, "
				         + "firstname VARCHAR (20) , "
				         + "lastname VARCHAR (20) , "
				         + "username VARCHAR (20) , "
				         + "password VARCHAR (20) , "
				         + "city VARCHAR (20) , "
				         + "mailID VARCHAR (20) , "
				         + "mobileno INT , "
				         + "PRIMARY KEY (ID))";
				
		
		try(Statement stmt = connection.createStatement())
		{
			stmt.execute(createTable);
			System.out.println("Table created");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void createQuestionTable(Connection connection)
	{

		 String createTable = "CREATE TABLE questionBank("
		         + "questionID INT NOT NULL AUTO_INCREMENT, "
		         + "question VARCHAR (100) , "
		         + "options VARCHAR (100) , "
		         + "ans VARCHAR (100) , "
		         + "PRIMARY KEY (questionID))";
		 
		 try(Statement stmt = connection.createStatement())
			{
				stmt.execute(createTable);
				System.out.println("Table created");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	
	public static void createResultTable(Connection connection)
	{
		String createTable = "CREATE TABLE result("
		         +  "resultID INT NOT NULL AUTO_INCREMENT, "
		         + "username VARCHAR (100) , "
		         + "marks INT , "
		         + "result VARCHAR(50) , "
		         + "PRIMARY KEY (resultID))";
		
		try(Statement stmt = connection.createStatement())
		{
			stmt.execute(createTable);
			System.out.println("Table created");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
