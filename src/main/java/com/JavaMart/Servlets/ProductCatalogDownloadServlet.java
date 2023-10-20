package com.JavaMart.Servlets;

import com.JavaMart.Classes.*;
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		File file = Staff.DownloadProductList();
		
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