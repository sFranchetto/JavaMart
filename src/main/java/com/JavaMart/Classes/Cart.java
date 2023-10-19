package com.JavaMart.Classes;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	public static List<Product> cart;

    public Cart() {
        Cart.cart = new ArrayList<>();
    }
	
	//TODO
	public static List<Product> getCart(String user) {
		 return cart;
	}
	
	//TODO
	public void AddProductToCart(String user, String sku) {
		System.out.println("User: " + user);
		System.out.println("Sku: " + sku);
		Product product = Product.GetProduct(sku);
		cart.add(product);
		System.out.println(cart.size());
		showCart();
	}
			
	//TODO
	public void RemoveProductFromCart(Product product) {
				
	}
	
	public static void showCart() {
	    for (Product product : cart) {
	        System.out.println("Product Name: " + product.getName());
	        System.out.println("Product SKU: " + product.getSKU());
	        // Print other product details as needed
	    }
	}
}
