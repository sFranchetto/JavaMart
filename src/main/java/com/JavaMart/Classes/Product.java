package com.JavaMart.Classes;

import java.util.List;

import com.JavaMart.ProductFactory;

public class Product {
	
	String name;
	String description;
	String vendor;
	String urlSlug;
	double price;
	String SKU;
	
	public Product() {
	}
	
	public Product(String name, String description, String vendor, String urlSlug, double price, String SKU) {
		this.name = name;
		this.description = description;
		this.vendor = vendor;
		this.urlSlug = urlSlug;
		this.price = price;
		this.SKU = SKU;
	}
	
	// Getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public double getPrice() {
        return price;
    }

    public String getSKU() {
        return SKU;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public static Product GetProductBySlug(String slug) {
    	List<Product> products = ProductFactory.returnAllProducts();
    	slug = slug.substring(1);
    	for (Product product : products) {
    		if(product.getUrlSlug().equals(slug)) {
    			return product;
    		}
    	}
    	System.out.println("Returning null");
    	return null; //product not found by slug name.
    }
    
    public static Product GetProduct(String sku) {
    	List<Product> products = ProductFactory.returnAllProducts();
    	for (Product product : products) {
    		if(product.getSKU().equals(sku)) {
    			return product;
    		}
    	}
    	System.out.println("Returning null");
    	return null; //product was not found by sku
    }
}
