package com.JavaMart.Classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.JavaMart.DatabaseManager;

public class Order {
	public static List<Order> order;
	String user;
	int id;
	static boolean isShipped;
	String trackingNum;
	String shipping_address;
	
	public Order(String user, int id, String shipping_address, boolean isShipped, String trackingNum) {
		this.user = user;
		this.id = id;
		this.shipping_address = shipping_address;
		this.isShipped = isShipped;
		this.trackingNum = trackingNum;
	}
	
	public Order() {
	    
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
	
	public String getShippingAddress() {
		return shipping_address;
	}
	
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}
	
	public static void shipOrder(String trackingNum) {
		isShipped = true;
		trackingNum = trackingNum;
	}
	
	public List<Order> orders;
	
	public static int CreateOrder(String user, String shipping_address) throws SQLException, ClassNotFoundException {
		int order_id = DatabaseManager.createOrder(user, shipping_address);
		DatabaseManager.createOrderDetails(order_id, user);
		Cart.clearCart(user);
		return order_id;
	}
	
	
//	public static List<Order> getOrders(String user) throws SQLException{
//		
//	}
//	
//	public static List<OrderDetail> getOrder(String user, int id) throws SQLException {
//		
//	}
//	
//	public static List<Order> GetAllOrders() throws SQLException{
//		
//	}
//	
//	public static void ShipOrder(int id, String trackingNumber) throws SQLException{
//		
//	}
}

