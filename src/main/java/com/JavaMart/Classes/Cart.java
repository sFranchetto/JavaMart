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
		Product product = Product.GetProduct(sku);
		cart.add(product);
	}
			
	//TODO
	public void RemoveProductFromCart(String user, String sku) {
		Product product = Product.GetProduct(sku);
		cart.remove(product);
	}
}
