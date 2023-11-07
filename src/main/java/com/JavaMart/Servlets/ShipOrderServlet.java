package com.JavaMart.Servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

import com.JavaMart.DatabaseManager;
import com.JavaMart.OperationNotAllowedException;
import com.JavaMart.Classes.Order;

@WebServlet("/order/shiporder/*")
public class ShipOrderServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String slug = req.getPathInfo();
		int orderId = Integer.parseInt(slug.substring(1));
		String trackingNum = req.getParameter("trackingNum");
		
		try {
			Order.ShipOrder(orderId, trackingNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.sendRedirect("/JavaMart/orders?method=staff_orders");
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		String passcode = (String) session.getAttribute("passcode");
		
		if (passcode != null && session.getAttribute("isStaff") == null) {
			try {
				throw new OperationNotAllowedException("Customers are not allowed to ship an order.");
			}catch (OperationNotAllowedException e) {
                req.setAttribute("e", e);
                req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
            }
		}else {
		
			String slug = req.getPathInfo();
			slug = slug.substring(1);
			
	
			req.setAttribute("orderNum", slug);
			req.getRequestDispatcher("/pages/shiporderform.jsp").forward(req, res);
		}
	}
}
