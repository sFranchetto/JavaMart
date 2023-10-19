package com.JavaMart.Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		session.setAttribute("isStaff", false);
		session.invalidate();
		res.sendRedirect("/JavaMart/pages/logout.jsp");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {doPost(req, res); }
		
		
}
