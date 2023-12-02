package com.JavaMart.Servlets.product;

import com.JavaMart.*;
import java.io.PrintWriter;
import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/products")
public class ProductServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		List<Product> products = ProductFactory.returnAllProducts();

		req.setAttribute("products", products);
		req.getRequestDispatcher("./pages/products.jsp").forward(req, res);
	}
}

