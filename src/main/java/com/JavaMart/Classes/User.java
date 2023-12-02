package com.JavaMart.Classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.JavaMart.DatabaseManager;
import com.JavaMart.ProductFactory;

public class User {
    
    private int id;
    private String passcode;
    private String userType;

    public User(int id, String passcode, String userType) {
        this.id = id;
        this.passcode = passcode;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getPasscode() {
        return passcode;
    }

    public String getUserType() {
        return userType;
    }
    

    public static void updateProduct(String oldSKU, String name, String description, String vendor, String urlSlug, double price, String newSKU) {
        Product product = Product.GetProduct(oldSKU);
        product.setName(name);
        product.setDescription(description);
        product.setVendor(vendor);
        product.setUrlSlug(urlSlug);
        product.setPrice(price);
        product.setSKU(newSKU);
    }

    public static void createProduct(String name, String sku) {
        Product product = new Product();
        product.setName(name);
        product.setUrlSlug(name);
        product.setSKU(sku);

        ProductFactory.add(product);
    }

    public static File downloadProductList(HttpServletRequest req) {
            List<Product> products = ProductFactory.returnAllProducts();
            String contextPath = req.getServletContext().getRealPath("/");
            File file = new File(contextPath + "products.csv");

            try (FileWriter writer = new FileWriter(file)) {
                writer.append("Name,Description,Vendor,URL_slug,SKU,price\n");
                for (Product product : products) {
                    writer.append(product.getName()).append(",").append(product.getDescription()).append(",")
                            .append(product.getVendor()).append(",").append(product.getUrlSlug()).append(",")
                            .append(product.getSKU()).append(",").append(String.valueOf(product.getPrice())).append("\n");
                }

            } catch (IOException e) {
                System.out.println("An error occurred while creating the CSV file.");
                e.printStackTrace();
            }
            return file;
    }
    
    public static boolean SetPasscode(int id, String passcode) throws ClassNotFoundException, SQLException {
    	boolean worked = DatabaseManager.changePasscode(id, passcode);
    	return worked;
    }
    
    public static void ChangePermission(int user_id) {
    	DatabaseManager.updateUserType(user_id);
    }
}
