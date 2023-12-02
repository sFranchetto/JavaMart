package com.JavaMart.Servlets.order;

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

@WebServlet("/claim_order")
public class ClaimOrderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/pages/claim_order.jsp").forward(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
	    String order_id_str = req.getParameter("orderNum");
	    int order_id = Integer.parseInt(order_id_str);
	    String passcode = (String) session.getAttribute("passcode");
	    boolean worked = false;
	    try {
			worked = Order.SetOrderOwner(order_id, passcode);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    if(worked) {
	    	session.setAttribute("successMessage", "Order has been claimed!");
	    	res.sendRedirect("/JavaMart/products");
	    }else {
	    	session.setAttribute("failureMessage_claim", "Error, Order ID does not exist or it's already claimed!");
	    	res.sendRedirect("/JavaMart/claim_order");
	    }
	}
	
}
