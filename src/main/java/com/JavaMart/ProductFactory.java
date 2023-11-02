package com.JavaMart;

import java.util.ArrayList;
import java.util.List;
import com.JavaMart.Classes.*;

public class ProductFactory {
	
	static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product("iPhone", "White, used to make calls", "Ali Baba", "iPhone", 499.99, "1"));
		products.add(new Product("Razer Keyboard", "Keyboard with red swtiches", "Razer", "razer-keyboard", 129.99, "2"));
		products.add(new Product("Beats Studio 3", "Active Noise Cancelling", "Amazon", "beats-studio-3", 39.99, "3"));
		products.add(new Product("Hydro Flask", "1.4Oz stainless steel water bottle", "Sports Experts", "hydro-flask", 49.99, "4"));
		products.add(new Product("Fender Guitar", "6 string with adjustable knobs", "Ebay", "fender", 274.99, "5"));
		products.add(new Product("Samsung Galaxy S21", "Android smartphone with 5G capability", "Samsung", "samsung-galaxy-s21", 799.99, "6"));
		products.add(new Product("Logitech MX Master 3", "Wireless computer mouse with customizable buttons", "Logitech", "logitech-mx-master-3", 99.99, "7"));
		products.add(new Product("Sony WH-1000XM4", "Wireless noise-canceling headphones with microphone", "Sony", "sony-wh-1000xm4", 349.99, "8"));
		products.add(new Product("Dell XPS 13", "13-inch laptop with Intel Core i7 processor and 16GB RAM", "Dell", "dell-xps-13", 1299.99, "9"));
		products.add(new Product("Nintendo Switch", "Hybrid gaming console with detachable controllers", "Nintendo", "nintendo-switch", 299.99, "10"));
		products.add(new Product("Bose QuietComfort Earbuds", "True wireless noise-canceling earbuds", "Bose", "bose-quietcomfort-earbuds", 199.99, "11"));
		products.add(new Product("Amazon Echo Dot", "Smart speaker with Alexa", "Amazon", "amazon-echo-dot", 49.99, "012"));
		products.add(new Product("Microsoft Surface Pro 7", "12.3-inch touchscreen 2-in-1 laptop", "Microsoft", "microsoft-surface-pro-7", 899.99, "13"));
		products.add(new Product("GoPro HERO9 Black", "5K Ultra HD action camera with front display", "GoPro", "gopro-hero9-black", 449.99, "14"));
		products.add(new Product("Canon EOS Rebel T8i", "24.1MP APS-C CMOS sensor DSLR camera", "Canon", "canon-eos-rebel-t8i", 899.99, "15"));
	};
	
	
	public static void add(Product product) {
		products.add(new Product(product.getName(), "", "", product.getUrlSlug(), 0.0, product.getSKU()));
	}
	
	public static List<Product> returnAllProducts(){
		return products;
	}
}
