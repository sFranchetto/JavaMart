package com.JavaMart;

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
		//System.out.println(slug);
		Product product = Product.getProductBySlug(slug);
		if (product != null) {
			req.setAttribute("product", product);
			req.getRequestDispatcher("/pages/product_details.jsp").forward(req, res);
			
		}else {
			System.out.println("slug doesn't exist");
		}
	}

}
