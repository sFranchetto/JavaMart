package com.JavaMart.Classes;

import java.util.List;

import com.JavaMart.ProductFactory;

public class User{
	
	static String user;
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public static String getUser() {
        return user;
    }
	
	public static class Customer extends User{
		
		public Customer() {
			setUser("TempUser");
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



