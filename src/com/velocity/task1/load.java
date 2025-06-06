package com.velocity.task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class load {
	
	private static final String URL = "jdbc:mysql://localhost:3306/test2";
	private static final String USER = "root";		
	private static final String PASS = "root";
			

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		try 
		{
			// Step 1 : Load the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Step 2 : Establish the Connection
			
			Connection con =DriverManager.getConnection(URL,USER,PASS);
			
			insertData(con,"Vartika",23);
		
			// UpdateStudentAge(con,27,4);
			
			
			// deleteStudent(con,4);
			
			//getStudentById(con,1);
			
			getAllStudents(con);
			
			
			
			
		}
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		}
		

	}
	
	
	public static void insertData(Connection connection , String name , int age)
	{
		 String insertSQL = " INSERT INTO student(name , age) VALUES (?,?)";
	
		
		try(PreparedStatement ps = connection.prepareStatement(insertSQL))
		{
		   
		    ps.setString(1,name);
		    ps.setInt(2, age);
		    
		   int updated = ps.executeUpdate();
		   System.out.println("Insert is Done : " + updated);
		 
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		
			
		
	}
	
	
	public static void UpdateStudentAge(Connection connection , int newAge, int rollno) throws SQLException
	{
		String updateSQL = "UPDATE student SET age = ? where rollno = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(updateSQL))
		{
			ps.setInt(1, newAge);
			ps.setInt(2,rollno);
			
			int updateRow = ps.executeUpdate();
			System.out.println("Update Done :" + updateRow);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static void deleteStudent(Connection connection, int rollno) throws SQLException
	{
		String updateSQL = "DELETE FROM student WHERE rollno =  ? ";
		 
		try(PreparedStatement ps = connection.prepareStatement(updateSQL))
		{
			ps.setInt(1, rollno);

			
			int updateRow = ps.executeUpdate();
			System.out.println("Update Done :" + updateRow);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void getStudentById(Connection connection, int rollno) {
		String selectSQL = "SELECT * FROM student WHERE rollno = ?";
		try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			// set the values
			ps.setInt(1, rollno);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					// extract the details from result
					int sid = rs.getInt("rollno");
					String name = rs.getString("name");
					int age = rs.getInt("age");

					System.out.println("Student Id : " + sid);
					System.out.println("Student Name : " + name);
					System.out.println("Student Age : " + age);
				} else {
					System.out.println("Student with given id is not present.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllStudents(Connection connection) throws SQLException
	{
		String selectSQL = "Select * FROM student";
		
		try(Statement stm =connection.createStatement(); ResultSet rs = stm.executeQuery(selectSQL))
		{
			while(rs.next())
			{
				int sid = rs.getInt("rollno");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				System.out.println(" Roll No : " + sid + " Name : "+name + " Age : "+ age );
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		    
	}

}
