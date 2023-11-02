package com.JavaMart.Servlets;

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
	
	private final String passcode = "secret";
	String path = "/UserPasscodes.txt";
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String userInputForStaff = req.getParameter("staffPassword");
		String userInputForUser = req.getParameter("userPassword");
		res.setContentType("text/html");
		HttpSession session = req.getSession();
		
		if(userInputForStaff == null) {
			try {
				if(userInputForUser != null) {
					FileWriter out = new FileWriter(getPath() + path);
					BufferedWriter bufferedWriter = new BufferedWriter(out);
					bufferedWriter.write(userInputForUser);
					bufferedWriter.close();
					session.setAttribute("isCustomer", true);
					session.setAttribute("passcode", userInputForUser);
					Connection con = DatabaseManager.RunDB();
					ResultSet rs = DatabaseManager.getStatement("SELECT * FROM USER WHERE ID = '" + userInputForUser+"'", con);
					if(rs == null) {
						DatabaseManager.insertStatement("INSERT INTO user (id)"
							+ "	VALUES ('" + userInputForUser + "')", con);
					}
					res.sendRedirect("./products");
				}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} else {
			if(userInputForStaff.equals(passcode) && userInputForStaff != null) {
				session.setAttribute("isStaff", true);
				res.sendRedirect("./products");
			}else {
				session.setAttribute("isStaff", false);
				req.setAttribute("message", "Incorrect Passcode");
				res.sendRedirect("./login");
			}
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
			
		req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
	}
	
	private String getPath() {
		String apath = "C:/Users/Steven/Desktop/Fall 2023/SOEN 387/ProjectFolder/JavaMart/src/main/webapp/resources/";
		return apath;
	}
}
