package com.JavaMart.Classes;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	public static List<Product> cart;

    public Cart() {
        Cart.cart = new ArrayList<>();
    }
	
	public static List<Product> getCart(String user) {
		return cart;
	}
	
	public void AddProductToCart(String user, String sku) {
		Product product = Product.GetProduct(sku);
		cart.add(product);
	}
			
	public void RemoveProductFromCart(String user, String sku) {
		Product product = Product.GetProduct(sku);
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
}
