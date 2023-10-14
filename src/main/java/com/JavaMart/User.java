package com.JavaMart;

import java.util.List;

class User{
	String userId;
}

class Customer extends User{
	List<Product> cart;
	
	//TODO
	public void getCart() {
		
	}
	
	//TODO
	public void addProductToCart(Product product) {
		
	}
	
	//TODO
	public void adjustQuantity (Product product, int quantity) {
		
	}
}

class Staff extends User{
	
	public static void UpdateProduct(String SKU, String name, String description, String vendor, String urlSlug, double price) {
		urlSlug = "/"+urlSlug;
		Product product = Product.GetProductBySlug(urlSlug);
		urlSlug = urlSlug.substring(1); //annoying to always have to do this T.T
		product.setName(name);
		product.setDescription(description);
		product.setVendor(vendor);
		product.setUrlSlug(urlSlug);
		product.setPrice(price);
		product.setSKU(SKU);
	}
	
	public static void CreateProduct(String name, String sku){
		Product product = new Product();
    	product.setName(name);
    	product.setSKU(sku);
    	
    	ProductFactory.add(product);	
	}
	
	public List<Product> downloadProductList(){
		
		return null;
	}
}