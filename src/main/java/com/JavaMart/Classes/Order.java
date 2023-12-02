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
	boolean isShipped;
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
	
	
	public List<Order> orders;
	
	//Creates an order after a user checks out in their cart
	public static int CreateOrder(String user, String shipping_address) throws SQLException, ClassNotFoundException {
		int order_id = DatabaseManager.createOrder(user, shipping_address);
		DatabaseManager.createOrderDetails(order_id, user);
		Cart.clearCart(user);
		return order_id;
	}
	
	//Returns all orders made by one user
	public static List<Order> GetOrders(String user) throws SQLException, ClassNotFoundException{
		List<Order> orders = new ArrayList<>();
		orders = DatabaseManager.getOrdersByUserPasscode(user);
		return orders;
	}
	
	//Returns on order and it's details given an order id
	public static List<OrderDetail> getOrder(int id) throws SQLException, ClassNotFoundException {
		List<OrderDetail> order_detail= new ArrayList<>();
		order_detail = DatabaseManager.getOrderDetails(id);
		return order_detail;
		
	}
	
	//Returns all orders made by all users
	public static List<Order> GetAllOrders() throws SQLException, ClassNotFoundException{
		List<Order> all_orders = new ArrayList<>();
		all_orders = DatabaseManager.getAllOrder();
		return all_orders;
	}
	
	//Ships the order, sets the isShipped to true and attaches the tracking number entered by the staff.
	public static void ShipOrder(int id, String trackingNumber) throws SQLException, ClassNotFoundException{
		DatabaseManager.shipOrder(id, trackingNumber);
		System.out.println("Shipped!" + id + " " + trackingNumber);
	}
	
	//Sets an unclaimed order to a logged in user
	public static boolean SetOrderOwner(int orderId, String passCode) throws ClassNotFoundException, SQLException {
		boolean worked = DatabaseManager.claimOrder(orderId, passCode);
		return worked;
	}
}

