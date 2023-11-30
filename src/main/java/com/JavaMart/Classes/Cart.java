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
		DatabaseManager.addProductToCart(user, sku, 0);
	}
			
	public void removeProductFromCart(String user, String sku) {
		
	}
	
	public void setProductQuantityInCart(String user, String sku, int quantity) {
		

	}
	
//	public int getProductQuantityInCart(String user, String sku) {
//	   
//	}
	
	public static void clearCart(String user) {
		
	}
}
