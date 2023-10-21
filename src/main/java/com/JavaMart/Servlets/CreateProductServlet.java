package com.JavaMart.Servlets;
import com.JavaMart.OperationNotAllowedException;
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

@WebServlet("/create_product")
public class CreateProductServlet extends HttpServlet{
	
	String user;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		if(session.getAttribute("isStaff").equals(true)){
			String productName = req.getParameter("productName");
			String productSKU = req.getParameter("productSKU");    
			Staff.CreateProduct(productName, productSKU);
		}
		res.sendRedirect("./products");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		user = CheckSession(session);
		if (!(user.equals("staff"))) {
			try {
				throw new OperationNotAllowedException("Customers are not allowed to create a new product.");
			}catch (OperationNotAllowedException e) {
                req.setAttribute("e", e);
                req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
            }
		}else {
			req.getRequestDispatcher("/pages/create_product.jsp").forward(req, res);
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
