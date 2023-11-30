package com.JavaMart.Classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.JavaMart.ProductFactory;

public class User {
    
    private int id;
    private String passcode;
    private static String userType;

    public User(int id, String passcode, String userType) {
        this.id = id;
        this.passcode = passcode;
        setUserType(userType);
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

    private void setUserType(String userType) {
        if (userType.equalsIgnoreCase("staff")) {
            this.userType = userType;
        } else {
            this.userType = "customer";
        }
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
        if ("staff".equals(userType)) {
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
        return null;
    }
}
