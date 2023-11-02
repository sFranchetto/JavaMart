package com.JavaMart.Classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.JavaMart.DatabaseManager;

public class Order {
	String user;
	int id;
	boolean isShipped;
	String trackingNum;
	
	public Order(String user, int id) {
		this.user = user;
		this.id = id;
		this.isShipped = false;
		this.trackingNum = null;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean getIsShipped() {
		return isShipped;
	}
	
	public String getTrackingNum() {
		return trackingNum;
	}
	
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}
	
	public void shipOder(String trackingNum) {
		this.isShipped = true;
		this.trackingNum = trackingNum;
	}
	
	public List<Order> orders;
	
	public static void CreateOrder(String user, String shipping_address) {
		Connection con = DatabaseManager.RunDB();
		String stmt = "INSERT INTO Orders (user_id, shipping_address) VALUES('"+user+"','"+shipping_address+"')";
		DatabaseManager.insertStatement(stmt, con);
		Cart.ClearCart(user);
	}
	
	
	public List<Order> getOrders(String user){
		List<Order> orders = new ArrayList<>();
		for(Order order : orders) {
			if(order.getUser().equals(user)) {
				orders.add(order);
			}
		}
		return orders;
	}
	
	public Order getOrder(String user, int id) {
		for (Order order : orders) {
			if (order.getId() == id) {
				return order; 
			}else {
				System.out.println("User doesn't have this order!");
			}
		}return null;
	}
	
}
