package com.JavaMart.Servlets;

import com.JavaMart.Classes.*;
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String slug = req.getPathInfo();
		Product product = Product.GetProductBySlug(slug);
		System.out.println(product.getName());
		
		req.setAttribute("product", product);
		req.setAttribute("slug", slug);
		req.getRequestDispatcher("/pages/edit_product.jsp").forward(req, res);
		
	}
}