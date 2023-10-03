package com.JavaMart;

import java.util.ArrayList;
import java.util.List;

public class ProductFactory {
	
	static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product("Product 1", "Description for Product 1", "Vendor A", "product-1", 19.99, "SKU-001"));
		products.add(new Product("Product 2", "Description for Product 2", "Vendor B", "product-2", 29.99, "SKU-002"));
		products.add(new Product("Product 3", "Description for Product 3", "Vendor C", "product-3", 39.99, "SKU-003"));
		products.add(new Product("Product 4", "Description for Product 4", "Vendor D", "product-4", 49.99, "SKU-004"));
		products.add(new Product("Product 5", "Description for Product 5", "Vendor E", "product-5", 59.99, "SKU-005"));
	};
	
	public static List<Product> returnAllProducts(){
		return products;
	}
}
