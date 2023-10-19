package com.JavaMart.Servlets;

import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User.Customer;
import com.JavaMart.Classes.User.Staff;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart/products/*")
public class ModifyCartServlet extends HttpServlet {
	
	Cart cart = new Cart();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		
		
		String slug = req.getPathInfo();
		String user = Customer.getUser();	
		
		Product product = Product.GetProductBySlug(slug);
		
		cart.AddProductToCart(user, product.getSKU());
		System.out.println(cart.getClass());
		
		res.sendRedirect("/JavaMart/cart");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/pages/user_cart.jsp").forward(req, res);
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {}

}