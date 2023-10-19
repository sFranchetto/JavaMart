package com.JavaMart.Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	private final String passcode = "secret";
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String userInput = req.getParameter("password");
		HttpSession session = req.getSession();
		if(userInput.equals(passcode) && userInput != null) {
			session.setAttribute("isStaff", true);
			//System.out.println(session.getAttribute("isStaff"));
			res.sendRedirect("./products");
		}else {
			//System.out.println("sad");
			session.setAttribute("isStaff", false);
			res.sendRedirect("./login");
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		

		req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
	}
}
