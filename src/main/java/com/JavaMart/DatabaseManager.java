package com.JavaMart;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DatabaseManager {
	
	public static void RunDB() {
		Connection connection = null;
		System.out.println("Start your engines...");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://localhost:3306/javamart";
            String username = "root";
            String password = "abc123";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
//            Statement stmt = connection.createStatement();
//            String sql = "INSERT INTO frui values(44, 'blueberry')";
//            stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}