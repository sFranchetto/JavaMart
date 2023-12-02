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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String userInputForStaff = req.getParameter("staffPassword");
        String userInputForUser = req.getParameter("userPassword");

        HttpSession session = req.getSession();

        if (userInputForStaff != null) {
            try {
                String temp = DatabaseManager.login(userInputForStaff);
                String userType = DatabaseManager.getUserTypeFromPasscode(temp);

                if ("secret".equals(temp) || "staff".equals(userType)) {
                    session.setAttribute("isStaff", true);
                    session.setAttribute("isCustomer", false);
                    session.setAttribute("passcode", temp);
                    res.sendRedirect("./products");
                } else {
                    System.out.println("Invalid passcode for Staff");
                    req.setAttribute("errorMessage", "Invalid passcode for Staff");
                    req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
                    return;
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (userInputForUser != null) {
            try {
                String temp = DatabaseManager.login(userInputForUser);
                session.setAttribute("isCustomer", true);
                session.setAttribute("isStaff", false);
                session.setAttribute("passcode", temp);
                res.sendRedirect("./products");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
	}
}
