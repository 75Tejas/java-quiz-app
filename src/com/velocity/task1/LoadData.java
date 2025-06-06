package com.velocity.task1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoadData {

	public static void main(String[] args) {
		// created the property object to store key-value pairs
		Properties prop = new Properties();
		
		try {
			FileInputStream input = new FileInputStream("src/db.properties");
			
			//load data
			prop.load(input);
			
			//Read
			
			String url = prop.getProperty("db.url");
			String user = prop.getProperty("db.username");			
			String pass = prop.getProperty("db.password");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			
			
			// Call createTable outside try-catch in main
			
			createTable(connect);
			insertData(connect);
			
			
			connect.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	private static void createTable(Connection connect) {
		// TODO Auto-generated method stub
		
		
		
	}

	// created table manual in CLI
	private static void insertData(Connection connect) {
		String query = "insert into `insurance_tbl`\n" + "(`age`, `tenure_2`, `tenure_3`, `tenure_4`) values\n"
				+ " (18, '1.816', '2.649', '3.556'),\n" + " (19, '1.864', '2.719', '3.644'),\n"
				+ " (20, '1.902', '2.773', '3.714'),\n" + " (21, '1.931', '2.814', '3.767'),\n"
				+ " (22, '1.952', '2.844', '3.807'),\n" + " (23, '1.968', '2.869', '3.841'),\n"
				+ " (24, '1.983', '2.893', '3.875'),\n" + " (25, '1.998', '2.917', '3.913'),\n"
				+ " (26, '2.017', '2.948', '3.960'),\n" + " (27, '2.040', '2.988', '4.022'),\n"
				+ " (28, '2.071', '3.040', '4.101'),\n" + " (29, '2.111', '3.106', '4.202'),\n"
				+ " (30, '2.137', '3.118', '4.226'),\n" + " (31, '2.162', '3.128', '4.249'),\n"
				+ " (32, '2.188', '3.246', '4.426'),\n" + " (33, '2.274', '3.387', '4.635'),\n"
				+ " (34, '2.375', '3.554', '4.878'),\n" + " (35, '2.494', '3.747', '5.161'),\n"
				+ " (36, '2.631', '3.972', '5.484'),\n" + " (37, '2.789', '4.231', '5.842'),\n"
				+ " (38, '2.972', '4.527', '6.237'),\n" + " (39, '3.182', '4.873', '6.684'),\n"
				+ " (40, '3.233', '4.964', '6.812'),\n" + " (41, '3.497', '5.393', '7.371'),\n"
				+ " (42, '3.802', '5.866', '8.016'),\n" + " (43, '4.156', '6.394', '8.759'),\n"
				+ " (44, '4.562', '6.997', '9.607'),\n" + " (45, '5.027', '7.684', '10.566'),\n"
				+ " (46, '5.546', '8.454', '11.635'),\n" + " (47, '6.099', '9.303', '12.806'),\n"
				+ " (48, '6.698', '10.226', '14.072'),\n" + " (49, '7.344', '11.212', '15.417'),\n"
				+ " (50, '8.027', '12.252', '16.829'),\n" + " (51, '8.741', '13.336', '18.300'),\n"
				+ " (52, '9.482', '14.460', '19.822'),\n" + " (53, '10.248', '15.621', '21.398'),\n"
				+ " (54, '11.040', '16.825', '23.036'),\n" + " (55, '11.865', '18.082', '24.752'),\n"
				+ " (56, '12.732', '19.408', '26.567'),\n" + " (57, '13.654', '20.822', '28.511'),\n"
				+ " (58, '14.643', '22.345', '30.613'),\n" + " (59, '15.717', '24.002', '32.901'),\n"
				+ " (60, '16.892', '25.817', '35.408'),\n" + " (61, '18.183', '27.814', '38.166'),\n"
				+ " (62, '19.606', '30.012', '41.202'),\n" + " (63, '21.177', '32.434', '44.543'),\n"
				+ " (64, '22.906', '35.100', '48.216'),\n" + " (65, '24.811', '38.031', '52.245')";
		
		
		 try (Statement st = connect.createStatement()) { // Fixed variable name
	            int i = st.executeUpdate(query); // Execute the query
	            System.out.println(i + " records inserted successfully!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

		
	}


}
