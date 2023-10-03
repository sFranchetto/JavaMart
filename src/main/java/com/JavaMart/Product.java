package com.JavaMart;

public class Product {
	
	String name;
	String description;
	String vendor;
	String urlSlug;
	double price;
	String SKU;
	
	
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
}
