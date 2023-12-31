package com.JavaMart.Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JavaMart.DatabaseManager;

public class Cart {
	
	public static List<Product> cart = new ArrayList<>();
	
	//Gets a users cart given the user using system
	public static List<Product> getCart(String passcode) throws SQLException, ClassNotFoundException {
		return DatabaseManager.getUserCart(passcode);
	}
	
	//Adds a product to cart given the user using system & the products unique SKU
	public static void addProductToCart(String user, String sku) throws ClassNotFoundException, SQLException {
		DatabaseManager.addProductToCart(user, sku);
	}
	
	//Removes a product to cart given the user using system & the products unique SKU
	public static void removeProductFromCart(String user, String sku) throws ClassNotFoundException, SQLException {
		DatabaseManager.removeProductFromCart(user, sku);
		
	}
	
	//Sets the quantity of a singular product in the cart, this value is taken from the frontend
	public static void setProductQuantityInCart(String user, String sku, int quantity)  throws ClassNotFoundException, SQLException{
		DatabaseManager.updateProductQuantity(user, sku, quantity);
	}
	
	//Gets the quantity of a product in a cart, returns that quantity for a specific product
	public static int getProductQuantityInCart(String passcode, String sku) throws ClassNotFoundException, SQLException {
		int quantity = DatabaseManager.getProductQuantityInCart(passcode, sku);
		return quantity;
	}
	
	//Removes all products in a user's cart
	public static void clearCart(String user) {
		DatabaseManager.clearCart(user);
	}
}
