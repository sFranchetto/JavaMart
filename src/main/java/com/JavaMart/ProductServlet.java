package com.JavaMart;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		ProductFactory productCreate = new ProductFactory();
		List<Product> products = productCreate.returnAllProducts();
		
		req.setAttribute("products", products);
		req.getRequestDispatcher("./pages/products.jsp").forward(req, res);
	}
}
