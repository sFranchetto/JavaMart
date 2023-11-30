package com.JavaMart.Classes.cart;

import com.JavaMart.DatabaseManager;
import com.JavaMart.OperationNotAllowedException;
import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
	String user;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String passcode = (String) session.getAttribute("passcode");
		String method = req.getParameter("_method");
		
	    if ("delete".equals(method)) {
	        doDelete(req, res);
	    } else if ("patch".equals(method)){
	    	doPatch(req, res);	    	
	    }else {

			String slug = req.getPathInfo();
			Product product = Product.GetProductBySlug(slug);

			try {
				if(passcode == null) {
					Cart.addProductToCart(passcode, product.getSKU());
					session.setAttribute("passcode", "temp");
				}else {
					Cart.addProductToCart(passcode, product.getSKU());
				}
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res.sendRedirect("/JavaMart/cart");
	    }
	}
	
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		HttpSession session = req.getSession();
//		user = CheckSession(session);
//		if (user.equals("staff")) {
//			try {
//				throw new OperationNotAllowedException("Staff members are not allowed to access the cart.");
//			}catch (OperationNotAllowedException e) {
//                req.setAttribute("e", e);
//                req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
//            }
//		}else {
//			req.getRequestDispatcher("/pages/user_cart.jsp").forward(req, res);
//		}
//	}
//	
//	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//
//		HttpSession session = req.getSession();
//		String passcode = (String) session.getAttribute("passcode");
//		String slug = req.getPathInfo();
//			
//		
//		Product product = Product.GetProductBySlug(slug);
//		cart.RemoveProductFromCart(passcode, product.getSKU());
//		
//		res.sendRedirect("/JavaMart/cart");
//	}
//	
	public void doPatch(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("POSA");
		String slug = req.getPathInfo();
			
		
		Product product = Product.GetProductBySlug(slug);
		
		res.sendRedirect("/JavaMart/cart");
	}
//	
//	
//	private String CheckSession(HttpSession session) {
//		if(session.getAttribute("isStaff") == null) {
//			return "TempUser";
//		}else {
//			return "staff";
//		}
//	}
}