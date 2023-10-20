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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String user = Customer.getUser();
		
		List<Product> cart = Cart.getCart(user);
		
		System.out.println("============");
		System.out.println(cart.getClass());
		System.out.println(cart.size());
		Cart.showCart();
		
		req.setAttribute("cart", cart);
		req.getRequestDispatcher("/pages/user_cart.jsp").forward(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {}
}