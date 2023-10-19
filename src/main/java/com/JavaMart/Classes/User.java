package com.JavaMart.Classes;

import java.util.List;

import com.JavaMart.ProductFactory;

public class User{
	String userId;
	
	public class Customer extends User{
		List<Product> cart;
		
		//TODO
		public void getCart() {
			
		}
		
		//TODO
		public void AddProductToCart(Product product) {
			
		}
		
		//TODO
		public void RemoveProductFromCart(Product product) {
			
		}
	}
	
	public class Staff extends User{
		
		public static void UpdateProduct(String oldSKU, String name, String description, String vendor, String urlSlug, double price, String newSKU) {
			Product product = Product.GetProduct(oldSKU);
			product.setName(name);
			product.setDescription(description);
			product.setVendor(vendor);
			product.setUrlSlug(urlSlug);
			product.setPrice(price);
			product.setSKU(newSKU);
		}
		
		public static void CreateProduct(String name, String sku){
			Product product = new Product();
	    	product.setName(name);
	    	product.setUrlSlug(name);
	    	product.setSKU(sku);
	    	
	    	ProductFactory.add(product);	
		}
		
		public List<Product> downloadProductList(){
			
			return null;
		}
	}
}



