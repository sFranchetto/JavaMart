package com.JavaMart;

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
	
	
	Product newProduct = new Product();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String productName = req.getParameter("productName");
		String productSKU = req.getParameter("productSKU");    
		newProduct.CreateProduct(productName, productSKU);
		
		res.sendRedirect("./products");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		

		req.getRequestDispatcher("/pages/create_product.jsp").forward(req, res);
	}
}
