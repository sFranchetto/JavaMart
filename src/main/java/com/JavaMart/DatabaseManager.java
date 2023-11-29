package com.JavaMart;


import java.sql.*;

public class DatabaseManager {
	
	public static Connection RunDB() {
		Connection connection = null;
		//System.out.println("Start your engines...");
		
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
	
	public static int insertStatement(String statement, Connection con) {
	    //System.out.println(statement);
	    Statement stmt;
	    try {
	        stmt = con.createStatement();
	        String sql = statement;
	        stmt.executeUpdate(sql);

	        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
	        int last_inserted_id = 0;
	        if (rs.next()) {
	            last_inserted_id = rs.getInt(1);
	        }
	        rs.close();

	        return last_inserted_id;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	public static ResultSet getStatement(String statement, Connection con) {
	    //System.out.println(statement);
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