package com.JavaMart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.JavaMart.Classes.User;
import com.JavaMart.Classes.Cart;
import com.JavaMart.Classes.Product;

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
	
	public static String getUserTypeFromPasscode(String passcode) throws SQLException, ClassNotFoundException {
        String userType = null;

        try (Connection conn = doDbStuff()) {
            String selectSQL = "SELECT user_type FROM users WHERE passcode = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                pstmt.setString(1, passcode);
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        userType = resultSet.getString("user_type");
                    }
                }
            }
        }

        return userType;
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
	
	public static boolean changePasscode(String newPasscode, int id) throws SQLException, ClassNotFoundException {
	    try (Connection conn = doDbStuff()) {
	        if (!isPasscodeExists(newPasscode, conn)) {
	            String updateSQL = "UPDATE users SET passcode = ? WHERE id = ?";
	            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
	                pstmt.setString(1, newPasscode);
	                pstmt.setInt(2, id);
	                pstmt.executeUpdate();
	                return true;
	            }
	        } else {
	            System.out.println("Passcode already exists in the database. Choose a different one.");
	            return false;
	        }
	    }
	}

	// Helper method
	private static boolean isPasscodeExists(String passcode, Connection conn) throws SQLException {
	    String query = "SELECT COUNT(*) FROM users WHERE passcode = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, passcode);
	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                return count > 0;
	            }
	        }
	    }
	    return false; // Default to false if an error occurs
	}

	
	public static List<User> getAllUsers() throws SQLException, ClassNotFoundException {
	    List<User> userList = new ArrayList<>();

	    try (Connection conn = doDbStuff()) {
	        String selectSQL = "SELECT id, passcode, user_type FROM users";
	        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                while (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String passcode = resultSet.getString("passcode");
	                    String userType = resultSet.getString("user_type");

	                    User user = new User(id, passcode, userType);
	                    userList.add(user);
	                }
	            }
	        }
	    }

	    return userList;
	}

	public static boolean updateUserType(int userId) {
        try (Connection conn = doDbStuff()) {
            String selectSQL = "SELECT user_type FROM users WHERE id = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                selectStmt.setInt(1, userId);
                try (ResultSet resultSet = selectStmt.executeQuery()) {
                    if (resultSet.next()) {
                        String currentType = resultSet.getString("user_type");
                        String newType = "customer".equals(currentType) ? "staff" : "customer";

                        String updateSQL = "UPDATE users SET user_type = ? WHERE id = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                            updateStmt.setString(1, newType);
                            updateStmt.setInt(2, userId);
                            updateStmt.executeUpdate();
                            return true; 
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false; 
    }	
	
	public static List<Product> getUserCart(String passcode) throws SQLException, ClassNotFoundException {
	    List<Product> userCart = new ArrayList<>();

	    try (Connection conn = doDbStuff()) {
	        String selectSQL = "SELECT sku, quantity FROM cart WHERE user_id = (SELECT user_id FROM users WHERE passcode = ?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
	            pstmt.setString(1, passcode);

	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                while (resultSet.next()) {
	                    String sku = resultSet.getString("sku");
	                    int quantity = resultSet.getInt("quantity");

	                    // Assuming you have a method GetProduct that returns a Product by SKU
	                    Product product = Product.GetProduct(sku);
	                    if (product != null) {

	                        userCart.add(product);
	                    }
	                }
	            }
	        }
	    }

	    return userCart;
	}
	
	public static void addProductToCart(String passcode, String sku, int quantity) throws SQLException, ClassNotFoundException {
	    System.out.println(passcode);
		try (Connection conn = doDbStuff()) {
	        // Check if passcode is null
	        if (passcode == null) {
	            // If passcode is null, create a user with passcode "temp"
	            String createTempUserSQL = "INSERT INTO users (passcode, user_type) VALUES ('temp', 'customer')";
	            try (PreparedStatement createTempUserStmt = conn.prepareStatement(createTempUserSQL, Statement.RETURN_GENERATED_KEYS)) {
	                createTempUserStmt.executeUpdate();
	                
	                passcode = "temp";
	                // Add the product to the cart
	                addProductToCart(passcode, sku,0);
	            }
	        } else {
	            // If passcode is not null, proceed to add the product to the cart
	            int userId = getIDFromPasscode(passcode);

	            // Check if user exists and proceed to add the product to the cart
	            if (userId != -1) {
	                // Check if the product is already in the cart
	                String checkIfExistsSQL = "SELECT COUNT(*) AS count FROM cart WHERE user_id = ? AND sku = ?";
	                try (PreparedStatement checkIfExistsStmt = conn.prepareStatement(checkIfExistsSQL)) {
	                    checkIfExistsStmt.setInt(1, userId);
	                    checkIfExistsStmt.setString(2, sku);

	                    try (ResultSet resultSet = checkIfExistsStmt.executeQuery()) {
	                        resultSet.next();
	                        int count = resultSet.getInt("count");

	                        if (count > 0) {
	                            // If the product is already in the cart, update the quantity
	                            String updateQuantitySQL = "UPDATE cart SET quantity = quantity + ? WHERE user_id = ? AND sku = ?";
	                            try (PreparedStatement updateQuantityStmt = conn.prepareStatement(updateQuantitySQL)) {
	                                updateQuantityStmt.setInt(1, quantity);
	                                updateQuantityStmt.setInt(2, userId);
	                                updateQuantityStmt.setString(3, sku);
	                                updateQuantityStmt.executeUpdate();
	                            }
	                        } else {
	                            // If the product is not in the cart, insert a new row
	                            String insertSQL = "INSERT INTO cart (user_id, sku, quantity) VALUES (?, ?, ?)";
	                            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
	                                insertStmt.setInt(1, userId);
	                                insertStmt.setString(2, sku);
	                                insertStmt.setInt(3, quantity);
	                                insertStmt.executeUpdate();
	                            }
	                        }
	                    }
	                }
	            } else {
	                // Handle the case where user with the given passcode is not found
	                System.out.println("User not found for passcode: " + passcode);
	            }
	        }
	    } catch (SQLException e) {
	        // Handle SQL exception
	        e.printStackTrace();
	    }
	}

}
