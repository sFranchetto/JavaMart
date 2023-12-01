package com.JavaMart.Classes.cart;

import com.JavaMart.DatabaseManager;
import com.JavaMart.OperationNotAllowedException;
import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	String user;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		//user = CheckSession(session);
		String passcode = (String) session.getAttribute("passcode"); 
		String type ="";
		try {
			type = DatabaseManager.getUserTypeFromPasscode(passcode);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((type == null) || type.equals("customer")){
			List<Product> cart = null;
			
			try {
				cart = Cart.getCart(passcode);
				System.out.println(cart.size());
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("cart", cart);
			req.getRequestDispatcher("/pages/user_cart.jsp").forward(req, res);
		}else if (type.equals("staff")) {
			try {
				throw new OperationNotAllowedException("Staff members are not allowed to access the cart.");
			}catch (OperationNotAllowedException e) {
                req.setAttribute("e", e);
                req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
            }
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {}
	
	
}
