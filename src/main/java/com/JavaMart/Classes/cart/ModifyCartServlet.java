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
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String passcode = (String) session.getAttribute("passcode");
		String method = req.getParameter("_method");
		
	    if ("delete".equals(method)) {
	        doDelete(req, res);
	    } else if ("patch-dec".equals(method)){
	    	try {
				doPatchDec(req, res);
			} catch (ClassNotFoundException | IOException | ServletException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
	    }
	    else if ("patch-inc".equals(method)){
	    	try {
				doPatchInc(req, res);
			} catch (ClassNotFoundException | IOException | ServletException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
	    }else {

			String slug = req.getPathInfo();
			Product product = Product.GetProductBySlug(slug);
			
			try {
				if(passcode == null) {
					session.setAttribute("passcode", "temp");
					passcode = "temp";
					Cart.addProductToCart(passcode, product.getSKU());
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String passcode = (String) session.getAttribute("passcode"); 
		String type ="";
		try {
			type = DatabaseManager.getUserTypeFromPasscode(passcode);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (type.equals("staff")) {
			try {
				throw new OperationNotAllowedException("Staff members are not allowed to access the cart.");
			}catch (OperationNotAllowedException e) {
                req.setAttribute("e", e);
                req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
            }
		}else {
			req.getRequestDispatcher("/pages/user_cart.jsp").forward(req, res);
		}
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();
		String passcode = (String) session.getAttribute("passcode");
		String slug = req.getPathInfo();
			
		
		Product product = Product.GetProductBySlug(slug);
		try {
			Cart.removeProductFromCart(passcode, product.getSKU());
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.sendRedirect("/JavaMart/cart");
	}
	
	public void doPatchInc(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ClassNotFoundException, SQLException {
		System.out.println("inc");
		String slug = req.getPathInfo();
		HttpSession session = req.getSession();
		String passcode = (String) session.getAttribute("passcode");	
		
		Product product = Product.GetProductBySlug(slug);
		product.getSKU();
		
		Cart.setProductQuantityInCart(passcode,product.getSKU(), 1);
		
		res.sendRedirect("/JavaMart/cart");
	}
	
	public void doPatchDec(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ClassNotFoundException, SQLException {
	    System.out.println("dec");
	    String slug = req.getPathInfo();
	    HttpSession session = req.getSession();
	    String passcode = (String) session.getAttribute("passcode");

	    Product product = Product.GetProductBySlug(slug);
	    String sku = product.getSKU();

	    int currentQuantity = Cart.getProductQuantityInCart(passcode, sku);

	    if(currentQuantity == 1) {
	    	Cart.removeProductFromCart(passcode, product.getSKU());
	    }
	    else if (currentQuantity > 0) {
	        // Decrement the quantity
	        Cart.setProductQuantityInCart(passcode, sku, -1);
	    }
	    res.sendRedirect("/JavaMart/cart");
	}
}