package com.JavaMart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.JavaMart.Classes.User;
import com.JavaMart.Classes.Cart;
import com.JavaMart.Classes.Product;

public class DatabaseManager {

	private static Connection conn;
	static boolean flag = false;
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
	        String selectSQL = "SELECT user_id, sku, quantity FROM cart WHERE user_id = (SELECT id FROM users WHERE passcode = ?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
	            pstmt.setString(1, passcode);

	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                while (resultSet.next()) {
	                    String sku = resultSet.getString("sku");

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
	
	public static void addProductToCart(String passcode, String sku) throws SQLException, ClassNotFoundException {
	    
	    try (Connection conn = doDbStuff()) {
	        // Check if passcode is null
	    	String checkTempUserSQL = "SELECT COUNT(*) AS count FROM users WHERE passcode = 'temp'";
	    	PreparedStatement checkTempUserStmt = conn.prepareStatement(checkTempUserSQL);
	    	ResultSet tempUserResultSet = checkTempUserStmt.executeQuery();
	    	tempUserResultSet.next();
            int tempUserCount = tempUserResultSet.getInt("count");
	    	
	    	if (passcode.equals("temp") && flag == false && tempUserCount == 0) {
	        	// If passcode is null, create a user with passcode "temp"
	            String createTempUserSQL = "INSERT INTO users (passcode, user_type) VALUES ('temp', 'customer')";
	            try (PreparedStatement createTempUserStmt = conn.prepareStatement(createTempUserSQL, Statement.RETURN_GENERATED_KEYS)) {
	                createTempUserStmt.executeUpdate();

	                passcode = "temp";
	                // Add the product to the cart
	                
	                flag = true;
	                addProductToCart(passcode, sku);
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
	                            String updateQuantitySQL = "UPDATE cart SET quantity = quantity + 1 WHERE user_id = ? AND sku = ?";
	                            try (PreparedStatement updateQuantityStmt = conn.prepareStatement(updateQuantitySQL)) {
	                                updateQuantityStmt.setInt(1, userId);
	                                updateQuantityStmt.setString(2, sku);
	                                updateQuantityStmt.executeUpdate();
	                            }
	                        } else {
	                            // If the product is not in the cart, insert a new row with quantity 1
	                            String insertSQL = "INSERT INTO cart (user_id, sku, quantity) VALUES (?, ?, 1)";
	                            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
	                                insertStmt.setInt(1, userId);
	                                insertStmt.setString(2, sku);
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

	
	public static void updateProductQuantity(String passcode, String sku, int quantityChange) throws SQLException, ClassNotFoundException {
	    try (Connection conn = doDbStuff()) {
	        // Check if passcode is null
	        if (passcode == null) {
	            // Handle the case where passcode is null
	            System.out.println("Passcode is null. Cannot update quantity.");
	            return;
	        }

	        // If passcode is not null, proceed to update the product quantity in the cart
	        int userId = getIDFromPasscode(passcode);

	        // Check if user exists and proceed to update the product quantity in the cart
	        if (userId != -1) {
	            // Update the product quantity in the cart
	            String updateQuantitySQL = "UPDATE cart SET quantity = quantity + ? WHERE user_id = ? AND sku = ?";
	            try (PreparedStatement updateQuantityStmt = conn.prepareStatement(updateQuantitySQL)) {
	                updateQuantityStmt.setInt(1, quantityChange);
	                updateQuantityStmt.setInt(2, userId);
	                updateQuantityStmt.setString(3, sku);
	                updateQuantityStmt.executeUpdate();
	            }
	        } else {
	            // Handle the case where user with the given passcode is not found
	            System.out.println("User not found for passcode: " + passcode);
	        }
	    } catch (SQLException e) {
	        // Handle SQL exception
	        e.printStackTrace();
	    }
	}
	
	public static int getProductQuantityInCart(String passode, String sku) throws SQLException, ClassNotFoundException {
	    int quantity = 0;

	    try (Connection conn = doDbStuff()) {
	        String selectSQL = "SELECT quantity FROM cart WHERE user_id = (SELECT id FROM users WHERE passcode = ?) AND sku = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
	            pstmt.setString(1, passode);
	            pstmt.setString(2, sku);

	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                if (resultSet.next()) {
	                    quantity = resultSet.getInt("quantity");
	                }
	            }
	        }
	    }

	    return quantity;
	}
	
	public static void removeProductFromCart(String passcode, String sku) throws SQLException, ClassNotFoundException {
	    try (Connection conn = doDbStuff()) {
	        // Check if passcode is null
	        if (passcode == null) {
	            // Handle the case where passcode is null
	            System.out.println("Passcode is null. Cannot remove product from cart.");
	            return;
	        }

	        // If passcode is not null, proceed to remove the product from the cart
	        int userId = getIDFromPasscode(passcode);

	        // Check if user exists and proceed to remove the product from the cart
	        if (userId != -1) {
	            // Check if the product is in the cart
	            String checkIfExistsSQL = "SELECT COUNT(*) AS count FROM cart WHERE user_id = ? AND sku = ?";
	            try (PreparedStatement checkIfExistsStmt = conn.prepareStatement(checkIfExistsSQL)) {
	                checkIfExistsStmt.setInt(1, userId);
	                checkIfExistsStmt.setString(2, sku);

	                try (ResultSet resultSet = checkIfExistsStmt.executeQuery()) {
	                    resultSet.next();
	                    int count = resultSet.getInt("count");

	                    if (count > 0) {
	                        // If the product is in the cart, remove it
	                        String removeProductSQL = "DELETE FROM cart WHERE user_id = ? AND sku = ?";
	                        try (PreparedStatement removeProductStmt = conn.prepareStatement(removeProductSQL)) {
	                            removeProductStmt.setInt(1, userId);
	                            removeProductStmt.setString(2, sku);
	                            removeProductStmt.executeUpdate();
	                        }
	                    } else {
	                        // If the product is not in the cart, log a message
	                        System.out.println("Product with SKU " + sku + " not found in the cart for user " + passcode);
	                    }
	                }
	            }
	        } else {
	            // Handle the case where user with the given passcode is not found
	            System.out.println("User not found for passcode: " + passcode);
	        }
	    } catch (SQLException e) {
	        // Handle SQL exception
	        e.printStackTrace();
	    }
	}
	
	public static int createOrder(String passcode, String shipAddress) {
	    int orderId = -1; // Default value in case of failure

	    try (Connection conn = doDbStuff()) {
	        int userId = getIDFromPasscode(passcode);

	        // Check if user exists
	        if (userId != -1) {
	            // Insert the order into the order table and return the generated order ID
	            String createOrderSQL = "INSERT INTO orders (user_id, shipping_address) VALUES (?, ?) RETURNING order_id";
	            try (PreparedStatement createOrderStmt = conn.prepareStatement(createOrderSQL)) {
	                createOrderStmt.setInt(1, userId);
	                createOrderStmt.setString(2, shipAddress);

	                try (ResultSet resultSet = createOrderStmt.executeQuery()) {
	                    if (resultSet.next()) {
	                        orderId = resultSet.getInt("order_id");
	                        System.out.println("Order created with ID: " + orderId);
	                    } else {
	                        System.out.println("Failed to retrieve the generated order ID.");
	                    }
	                }
	            }
	        } else {
	            // Handle the case where user with the given passcode is not found
	            System.out.println("User not found for passcode: " + passcode);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        // Handle SQL exception
	        e.printStackTrace();
	    }
	    
	    System.out.println(orderId);
	    return orderId;
	}
	
	public static void createOrderDetails(int orderId, String passcode) {
	    try (Connection conn = doDbStuff()) {
	        // Get cart contents based on passcode
	        List<Product> cartContents = getUserCart(passcode);

	        // Insert order details into the order_details table for each item in the cart
	        String createOrderDetailsSQL = "INSERT INTO order_details (order_id, product_id, quantity) VALUES (?, ?, ?)";
	        try (PreparedStatement createOrderDetailsStmt = conn.prepareStatement(createOrderDetailsSQL)) {
	            for (Product product : cartContents) {
	                createOrderDetailsStmt.setInt(1, orderId);
	                createOrderDetailsStmt.setString(2, product.getSKU());
	                createOrderDetailsStmt.setInt(3, getProductQuantityInCart(passcode, product.getSKU()));
	                createOrderDetailsStmt.executeUpdate();
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        // Handle SQL or class not found exception
	        e.printStackTrace();
	    }
	}
	
	public static void clearCart(String user) {
	    try (Connection conn = doDbStuff()) {
	        // Get user ID from the passcode
	        int userId = getIDFromPasscode(user);

	        // Check if user exists
	        if (userId != -1) {
	            // Delete all entries in the cart for the specified user
	            String clearCartSQL = "DELETE FROM cart WHERE user_id = ?";
	            try (PreparedStatement clearCartStmt = conn.prepareStatement(clearCartSQL)) {
	                clearCartStmt.setInt(1, userId);
	                int rowsAffected = clearCartStmt.executeUpdate();

	                System.out.println(rowsAffected + " rows deleted from the cart for user: " + user);
	            }
	        } else {
	            // Handle the case where user with the given passcode is not found
	            System.out.println("User not found for passcode: " + user);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        // Handle SQL or class not found exception
	        e.printStackTrace();
	    }
	}
}
