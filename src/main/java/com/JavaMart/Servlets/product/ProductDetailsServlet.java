package com.JavaMart.Servlets.product;

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

@WebServlet("/products/*")
public class ProductDetailsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String slug = req.getPathInfo();
		
		Product product = Product.GetProductBySlug(slug);
		
		req.setAttribute("product", product);
		req.getRequestDispatcher("/pages/product_details.jsp").forward(req, res);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String slug = req.getPathInfo();
		if(slug.equals("/logout")) {
			res.sendRedirect("/JavaMart/pages/logout.jsp");
		}else {
			Product product = Product.GetProductBySlug(slug);
			String oldSKU = product.getSKU();
			String productName = req.getParameter("newName");
			String productDesc = req.getParameter("newDesc");
			String productVendor = req.getParameter("newVendor");
			String productSlug = req.getParameter("newSlug");
			double productPrice = Double.parseDouble(req.getParameter("newPrice"));
			String productSKU = req.getParameter("newSKU");
			
			Staff.UpdateProduct(oldSKU, productName, productDesc, productVendor, productSlug, productPrice, productSKU);
			slug = slug.substring(1);
			res.sendRedirect("./" + productSlug);
		}
	}

}
