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
        String userIdString = req.getParameter("userId");
        int userId = Integer.parseInt(userIdString);
        User.ChangePermission(userId);
        res.sendRedirect("/JavaMart/privileges");
}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		try {
            List<User> users = DatabaseManager.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/pages/priveleges_management.jsp").forward(req, res);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching users");
        }
		
	}
}
