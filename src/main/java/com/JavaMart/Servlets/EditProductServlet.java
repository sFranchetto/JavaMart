package com.JavaMart.Servlets;

import com.JavaMart.OperationNotAllowedException;
import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User.Customer;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/edit_product/*")
public class EditProductServlet extends HttpServlet{
	
	String user;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		user = CheckSession(session);
		
		if (!(user.equals("staff"))) {
			try {
				throw new OperationNotAllowedException("Customers are not allowed to edit products.");
			}catch (OperationNotAllowedException e) {
                req.setAttribute("e", e);
                req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
            }
		}else {
		
			String slug = req.getPathInfo();
			Product product = Product.GetProductBySlug(slug);		
			
			req.setAttribute("product", product);
			req.setAttribute("slug", slug);
			req.getRequestDispatcher("/pages/edit_product.jsp").forward(req, res);
		}
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String slug = req.getPathInfo();
		if(slug.equals("/logout")) {
			res.sendRedirect("/JavaMart/pages/logout.jsp");
		}
		
	}
	
	private String CheckSession(HttpSession session) {
		if(session.getAttribute("isStaff") == null) {
			return "TempUser";
		}else {
			return "staff";
		}
	}
}