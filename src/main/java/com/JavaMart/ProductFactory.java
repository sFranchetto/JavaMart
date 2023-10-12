package com.JavaMart;

import java.util.ArrayList;
import java.util.List;

public class ProductFactory {
	
	static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product("iPhone", "White, used to make calls", "Ali Baba", "iPhone", 499.99, "SKU-001"));
		products.add(new Product("Razer Keyboard", "Keyboard with red swtiches", "Razer", "razer-keyboard", 129.99, "SKU-002"));
		products.add(new Product("Beats Studio 3", "Active Noise Cancelling", "Amazon", "beats-studio-3", 39.99, "SKU-003"));
		products.add(new Product("Hydro Flask", "1.4Oz stainless steel water bottle", "Sports Experts", "hydro-flask", 49.99, "SKU-004"));
		products.add(new Product("Fender Guitar", "6 string with adjustable knobs", "Ebay", "fender", 274.99, "SKU-005"));
	};
	
	
	public static void add(Product product) {
		products.add(new Product(product.getName(), "", "", "", 0.0, product.getSKU()));
	}
	
	public static List<Product> returnAllProducts(){
		return products;
	}
}
