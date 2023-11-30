package com.JavaMart.Servlets.auth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

import com.JavaMart.DatabaseManager;

@WebServlet("/privileges")
public class PrivilegeManagerServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
//        String newPasscode = req.getParameter("passcode");
//        String passcode = (String) session.getAttribute("passcode");
//        
//        try {
//        	int id = DatabaseManager.getIDFromPasscode(passcode);
//        	DatabaseManager.changePasscode(newPasscode, id);
//
//        	session.setAttribute("successMessage", "Passcode successfully changed");
//        	session.setAttribute("passcode", newPasscode);
//        	res.sendRedirect("/JavaMart/products");
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//
//            req.setAttribute("errorMessage", "An error occurred while changing the passcode");
//            req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
//        }
    
}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/pages/priveleges_management.jsp").forward(req, res);
	}
}
