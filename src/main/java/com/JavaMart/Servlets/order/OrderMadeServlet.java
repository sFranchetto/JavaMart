package com.JavaMart.Servlets.order;

import com.JavaMart.*;
import java.io.PrintWriter;
import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/order_made")
public class OrderMadeServlet extends HttpServlet{
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		req.getRequestDispatcher("/pages/create_order.jsp").forward(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	    HttpSession session = req.getSession();
	    String ship_address = req.getParameter("shippingAddress");
	    String user_id = (String) session.getAttribute("passcode");

	    try {
	        int order_id = Order.CreateOrder(user_id, ship_address);
	        req.setAttribute("orderId", order_id);
	        res.sendRedirect("/JavaMart/pages/order_created.jsp?orderId=" + order_id);
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

}
