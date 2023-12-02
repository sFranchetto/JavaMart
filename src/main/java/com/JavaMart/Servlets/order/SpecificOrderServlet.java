package com.JavaMart.Servlets.order;

import java.io.IOException;
import java.sql.SQLException;

import com.JavaMart.DatabaseManager;
import com.JavaMart.Classes.*;

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
		
		String slug = req.getPathInfo();
		slug = slug.substring(1);
		int order_id = Integer.parseInt(slug);
		List<OrderDetail> details = null;
		boolean shipped = false;
		
		try {
			details = Order.getOrder(order_id);
			shipped = DatabaseManager.isOrderShipped(order_id);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		req.setAttribute("orderdetail", details);
		req.setAttribute("slug", slug);
		req.setAttribute("shipped", shipped);
		req.getRequestDispatcher("/pages/view_order.jsp").forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {}
}
