package com.JavaMart.Servlets;

import com.JavaMart.*;
import com.JavaMart.Classes.Order;
import com.JavaMart.Classes.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/orders")
public class AllOrdersServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("passcode");
		String method = req.getParameter("method");
		
		if("staff_orders".equals(method)) {
			List<Order> allOrders = null; 
			try {
				allOrders = Order.GetAllOrders();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			req.setAttribute("allOrders", allOrders);
			req.getRequestDispatcher("/pages/view_all_orders.jsp").forward(req, res);
		}else {
		
			List<Order> orders = null;
			
			try {
				orders = Order.getOrders(user_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("orders", orders);
			req.getRequestDispatcher("/pages/view_orders.jsp").forward(req, res);
		}
	}
}