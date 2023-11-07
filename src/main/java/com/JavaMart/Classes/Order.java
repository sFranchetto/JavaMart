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
	    // No-argument constructor
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
	
	public static void CreateOrder(String user, String shipping_address) throws SQLException {
		Connection con = DatabaseManager.RunDB();
		String stmt = "INSERT INTO Orders (user_id, shipping_address, isShipped) VALUES('"+user+"','"+shipping_address+"',"+ 0 +")";
		int id = DatabaseManager.insertStatement(stmt, con);
		//System.out.println(id);
		
		List<Product> cartProducts = Cart.getCart(user);
		
		for (Product product : cartProducts) {
			String create_order_deatils = "INSERT INTO orderdetails (order_id, product_id, quantity) VALUES ("
					+id+", "+product.getSKU() + ",1)";
			DatabaseManager.insertStatement(create_order_deatils, con);
		}
		Cart.ClearCart(user);
	}
	
	
	public static List<Order> getOrders(String user) throws SQLException{
		//System.out.println(user);
		String stmt = "SELECT * FROM orders WHERE user_id = '" + user + "'";
		ResultSet rs = DatabaseManager.getStatement(stmt, con);
		order = new ArrayList<Order>();
		
		if (rs == null) {
            order = new ArrayList<>();
        }else {
        	int order_id = rs.getInt("order_id");
			String user_id = rs.getString("user_id");
			String shipping_address = rs.getString("shipping_address");
			String tracking_num = rs.getString("tracking_number");
			boolean isShipped = rs.getBoolean("isShipped");
			Order orderDetail = new Order(user_id, order_id, shipping_address, isShipped, tracking_num);
			order.add(orderDetail);
        	while(rs.next()) {
				order_id = rs.getInt("order_id");
				user_id = rs.getString("user_id");
				shipping_address = rs.getString("shipping_address");
				tracking_num = rs.getString("tracking_number");
				orderDetail = new Order(user_id, order_id, shipping_address, isShipped, tracking_num);
				order.add(orderDetail);
			}
        }
		return order;
	}
	
	public static List<OrderDetail> getOrder(String user, int id) throws SQLException {
		String stmt = "SELECT * FROM orderdetails WHERE order_id = " + id;
		ResultSet rs = DatabaseManager.getStatement(stmt, con);
		OrderDetail.orderDetail = new ArrayList<>();
		int order_id = rs.getInt("order_id");
		String product_id = rs.getString("product_id");
		int quantity = rs.getInt("quantity");
		OrderDetail od = new OrderDetail(order_id, product_id, quantity);
		OrderDetail.orderDetail.add(od);
		while(rs.next()) {
			order_id = rs.getInt("order_id");
			product_id = rs.getString("product_id");
			quantity = rs.getInt("quantity");
			od = new OrderDetail(order_id, product_id, quantity);
			OrderDetail.orderDetail.add(od);
		}
		return OrderDetail.orderDetail;
	}
	
	public static List<Order> GetAllOrders() throws SQLException{
		String stmt = "SELECT * FROM Orders";
		ResultSet rs = DatabaseManager.getStatement(stmt, con);
		ArrayList<Order> allOrders = new ArrayList<>();
		if(rs == null) {
			Order noOrder = new Order();
			allOrders.add(noOrder);
			return allOrders;
		}else {
			int order_id = rs.getInt("order_id");
			String user_id = rs.getString("user_id");
			String shipping_address = rs.getString("shipping_address");
			String tracking_num = rs.getString("tracking_number");
			boolean isShipped = rs.getBoolean("isShipped");
			Order orderDetail = new Order(user_id, order_id, shipping_address, isShipped, tracking_num);
			allOrders.add(orderDetail);
			
		while(rs.next()) {
			user_id = rs.getString("user_id");
			shipping_address = rs.getString("shipping_address");
			tracking_num = rs.getString("tracking_number");
			order_id = rs.getInt("order_id");
			isShipped = rs.getBoolean("isShipped");
			orderDetail = new Order(user_id, order_id, shipping_address, isShipped, tracking_num);
			allOrders.add(orderDetail);
			}
			return allOrders;
		}
	}
	
	public static void ShipOrder(int id, String trackingNumber) throws SQLException{
		String stmt = "UPDATE Orders SET tracking_number = '" + trackingNumber + "', isShipped = true"
	            + " WHERE order_id = " + id;
		DatabaseManager.insertStatement(stmt, con);
		shipOrder(trackingNumber);
	}
}

