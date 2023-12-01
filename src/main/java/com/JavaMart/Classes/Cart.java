package com.JavaMart.Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JavaMart.DatabaseManager;

public class Cart {
	
	public static List<Product> cart = new ArrayList<>();
	
	public static List<Product> getCart(String passcode) throws SQLException, ClassNotFoundException {
		return DatabaseManager.getUserCart(passcode);
	}
	
	public static void addProductToCart(String user, String sku) throws ClassNotFoundException, SQLException {
		DatabaseManager.addProductToCart(user, sku);
	}
			
	public static void removeProductFromCart(String user, String sku) throws ClassNotFoundException, SQLException {
		DatabaseManager.removeProductFromCart(user, sku);
		
	}
	
	public static void setProductQuantityInCart(String user, String sku, int quantity)  throws ClassNotFoundException, SQLException{
		DatabaseManager.updateProductQuantity(user, sku, quantity);
	}
	
	public static int getProductQuantityInCart(String passcode, String sku) throws ClassNotFoundException, SQLException {
		int quantity = DatabaseManager.getProductQuantityInCart(passcode, sku);
		return quantity;
	}
	
	public static void clearCart(String user) {
		
	}
}
