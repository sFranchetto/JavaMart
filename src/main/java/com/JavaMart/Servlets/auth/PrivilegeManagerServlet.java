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
import com.JavaMart.Classes.User;

//import org.apache.catalina.User;

import java.sql.*;

import com.JavaMart.DatabaseManager;

@WebServlet("/privileges")
public class PrivilegeManagerServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String userIdString = req.getParameter("userId");
        int userId = Integer.parseInt(userIdString);
        DatabaseManager.updateUserType(userId);
        

        // Print the user ID to the console (you can replace this with your desired action)
        System.out.println("User ID: " + userId);
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
		try {
            List<User> users = DatabaseManager.getAllUsers();
            for (User user : users) {
                System.out.println("User ID: " + user.getId());
                System.out.println("Passcode: " + user.getPasscode());
                System.out.println("User Type: " + user.getUserType());
                System.out.println("------------------------");
            }
            req.setAttribute("users", users);
            req.getRequestDispatcher("/pages/priveleges_management.jsp").forward(req, res);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching users");
        }
		
	}
}
