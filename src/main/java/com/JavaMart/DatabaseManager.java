package com.JavaMart;


import java.sql.*;

public class DatabaseManager {
	
	public static Connection RunDB() {
		Connection connection = null;
		System.out.println("Start your engines...");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://localhost:3306/javamart";
            String username = "root";
            String password = "abc123";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
		}catch(Exception e) {
			System.out.println(e);
		}
		return connection;
	}
	
	public static void insertStatement(String statement, Connection con) {
		System.out.println(statement);
		Statement stmt;
		try {
			stmt = con.createStatement();
			String sql = statement;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet getStatement(String statement, Connection con) {
	    System.out.println(statement);
	    Statement stmt;
	    ResultSet rs = null;
	    try {
	        stmt = con.createStatement();
	        rs = stmt.executeQuery(statement);
	        if (!rs.next()) {
	            rs = null; // Set to null if no rows are found
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception appropriately
	    }
	    return rs;
	}
}