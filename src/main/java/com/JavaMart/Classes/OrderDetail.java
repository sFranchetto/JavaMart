package com.JavaMart.Classes;
import java.sql.Connection;

import java.util.List;

public class OrderDetail{
	private int order_id;
    private String product_id;
    private int quantity;
    public static List<OrderDetail> orderDetail;
    
    public OrderDetail(int order_id, String product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String id) {
        this.product_id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getProductName(String id) {
    	Product prod = Product.GetProduct(id);
    	String sku = prod.getName();
    	return sku;
    }
}
