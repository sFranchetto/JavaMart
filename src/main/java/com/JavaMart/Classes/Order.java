package com.JavaMart.Classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JavaMart.DatabaseManager;

public class Order {
	public static List<Order> order;
	static Connection con = DatabaseManager.RunDB();
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
	
	public void shipOder(String trackingNum) {
		this.isShipped = true;
		this.trackingNum = trackingNum;
	}
	
	public List<Order> orders;
	
	public static void CreateOrder(String user, String shipping_address) throws SQLException {
		Connection con = DatabaseManager.RunDB();
		String stmt = "INSERT INTO Orders (user_id, shipping_address) VALUES('"+user+"','"+shipping_address+"')";
		int id = DatabaseManager.insertStatement(stmt, con);
		System.out.println(id);
		
		List<Product> cartProducts = Cart.getCart(user);
		
		for (Product product : cartProducts) {
			String create_order_deatils = "INSERT INTO orderdetails (order_id, product_id, quantity) VALUES ("
					+id+", "+product.getSKU() + ",1)";
			DatabaseManager.insertStatement(create_order_deatils, con);
		}
		Cart.ClearCart(user);
	}
	
	
	public static List<Order> getOrders(String user) throws SQLException{
		System.out.println(user);
		String stmt = "SELECT * FROM orders WHERE user_id = '" + user + "'";
		ResultSet rs = DatabaseManager.getStatement(stmt, con);
		order = new ArrayList<Order>();
		
		if (rs == null) {
            order = new ArrayList<>();
        }else {
        	while(rs.next()) {
				int order_id = rs.getInt("order_id");
				String user_id = rs.getString("user_id");
				String shipping_address = rs.getString("shipping_address");
				String tracking_num = rs.getString("tracking_number");
				Order orderDetail = new Order(user_id, order_id, shipping_address, false, tracking_num);
				order.add(orderDetail);
			}
        }
		return order;
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
