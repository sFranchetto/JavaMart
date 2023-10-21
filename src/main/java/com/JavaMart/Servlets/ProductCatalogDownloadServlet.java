package com.JavaMart.Servlets;

import com.JavaMart.OperationNotAllowedException;
import com.JavaMart.Classes.*;
import com.JavaMart.Classes.User.Customer;
import com.JavaMart.Classes.User.Staff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/products/download")
public class ProductCatalogDownloadServlet extends HttpServlet {
	
	String user;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		
		File file = Staff.DownloadProductList();
		user = CheckSession(session);
		
		if (!(user.equals("staff"))) {
			try {
				throw new OperationNotAllowedException("Customers are not allowed to download product catalog.");
			}catch (OperationNotAllowedException e) {
                req.setAttribute("e", e);
                req.getRequestDispatcher("/common/error_page.jsp").forward(req, res);
            }
		}else {
			res.setContentType("text/csv");
			
			FileInputStream stream = new FileInputStream(file);
			ServletOutputStream outStream = res.getOutputStream();
			byte[] outputByte = new byte[4096];
			int bytesRead;
			
			while ((bytesRead = stream.read(outputByte, 0, 4096)) != -1) {
	            outStream.write(outputByte, 0, bytesRead);
	        }
			stream.close();
			outStream.flush();
			outStream.close();
		}
	}
	
	private String CheckSession(HttpSession session) {
		if(session.getAttribute("isStaff") == null) {
			return "TempUser";
		}else {
			return "staff";
		}
	}
}