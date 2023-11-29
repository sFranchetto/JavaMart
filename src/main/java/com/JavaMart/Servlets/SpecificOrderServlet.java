package com.JavaMart.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User.Staff;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/orders/*")
public class SpecificOrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("passcode");
		
		String slug = req.getPathInfo();
		slug = slug.substring(1);
		int order_id = Integer.parseInt(slug);
		List<OrderDetail> details = null;
		
		//System.out.println("in here" + order_id);
		
		try {
			details = Order.getOrder(user, order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		req.setAttribute("orderdetail", details);
		req.setAttribute("slug", slug);
		req.getRequestDispatcher("/pages/view_order.jsp").forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {}
}
