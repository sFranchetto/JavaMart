package com.JavaMart;

import java.sql.*;

public class DatabaseManager {

	private static Connection conn;

	public static Connection doDbStuff() throws ClassNotFoundException {
	    Connection conn = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	String jdbcUrl = "jdbc:sqlite:C:/Users/Steven/Desktop/FALL 2023/SOEN 387/ProjectFolder/JavaMart/src/main/webapp/common/db_stuff/javamartdb.db";
	    	conn = DriverManager.getConnection(jdbcUrl);
	        System.out.println("Opened database connection");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getClass().getName() + ": " + e.getMessage());
	    }
	    return conn;
	}
    
	public static int getIDFromPasscode(String passcode) throws SQLException, ClassNotFoundException {
        try (Connection conn = doDbStuff()) {
            String selectSQL = "SELECT id FROM users WHERE passcode = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                pstmt.setString(1, passcode);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id");
                    }
                }
            }
        }
        return -1; 
    }
	
	public static String login(String passcode) throws SQLException, ClassNotFoundException {
	    try (Connection conn = doDbStuff()) {
	        String loginSQL = "SELECT * FROM users WHERE passcode = (?)";
	        try (PreparedStatement stmt = conn.prepareStatement(loginSQL)) {
	            stmt.setString(1, passcode);
	            try (ResultSet rs = stmt.executeQuery()) {
	                System.out.println(loginSQL);
	                if (rs.next()) {
	                    System.out.println("User found");
	                    return rs.getString("passcode");
	                } else {
	                    if(passcode.equals("secret")) {
	                    	System.out.println("User not found, inserting...");
		                    String insertSQL = "INSERT INTO users (passcode, user_type) VALUES (?, ?)";
		                    try (PreparedStatement insertStatement = conn.prepareStatement(insertSQL)) {
		                        insertStatement.setString(1, passcode);
		                        insertStatement.setString(2, "staff");
		                        insertStatement.executeUpdate();
		                        System.out.println("Staff inserted");
		                        return passcode;
		                    }
	                    } else {
		                	System.out.println("User not found, inserting...");
		                    String insertSQL = "INSERT INTO users (passcode, user_type) VALUES (?, ?)";
		                    try (PreparedStatement insertStatement = conn.prepareStatement(insertSQL)) {
		                        insertStatement.setString(1, passcode);
		                        insertStatement.setString(2, "customer");
		                        insertStatement.executeUpdate();
		                        System.out.println("User inserted");
		                        return passcode;
		                    }
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return ""; 
	    }
	}
	
	public static void changePasscode(String newPasscode, int id) throws SQLException, ClassNotFoundException{
		try (Connection conn = doDbStuff()) {
            String updateSQL = "UPDATE users SET passcode = (?) WHERE id = (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                pstmt.setString(1, newPasscode);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }
        }
	}
}
