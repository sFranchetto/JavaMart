package com.JavaMart.Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JavaMart.DatabaseManager;

public class Cart {
	
	public static List<Product> cart;
	static Connection con = DatabaseManager.RunDB();
	
    public Cart() {
        Cart.cart = new ArrayList<>();
    }
	
	public static List<Product> getCart(String user) throws SQLException {
		System.out.println(user);
		String stmt = "SELECT sku, quantity FROM cart WHERE user_id = '" + user + "'";
		ResultSet rs = DatabaseManager.getStatement(stmt, con);
		
		
		if (rs == null) {
            cart = new ArrayList<>();
        }else {
        	while(rs.next()) {
				String sku = rs.getString("sku");
				System.out.println(sku);
				AddProductToCart(user, sku);//rs.getString("sku"));
			}
        }
		return cart;
	}
	
	public static void AddProductToCart(String user, String sku) {
		if (cart == null) {
	        cart = new ArrayList<>();
	    }
	    Product product = Product.GetProduct(sku);
	    if (!cart.contains(product)) {
	        cart.add(product);
	    }
	}
			
	public void RemoveProductFromCart(String user, String sku) {
		Product product = Product.GetProduct(sku);
		
		String stmt = "DELETE FROM cart WHERE user_id='" + user + "' AND sku='" + product.getSKU() + "'";
		DatabaseManager.insertStatement(stmt, con);
		
		System.out.println(cart.size());
		cart.remove(product);
	}
	
	public void SetProductQuantityInCart(String user, String sku, int quantity) {
		if(quantity <= 0) {
			System.out.println("Add more!");
		}
	}
	
	public int getProductQuantityInCart(String user, String sku) {
	    int quantity = 0;
	    for (Product product : cart) {
	        if (product.getSKU().equals(sku)) {
	            quantity++;
	        }
	    }
	    return quantity;
	}
	
	public static void ClearCart(String user) {
		String statement = "DELETE FROM cart WHERE user_id = '" + user +"'";
		DatabaseManager.insertStatement(statement, con);
	}
}
