<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	//Spaghetti code
	session.setAttribute("isStaff", false);
	session.invalidate();
%>
<h1>You have logged out</h1>
<h2>Click <a href="/JavaMart/products">here</a> to return to product catalog.</h2>
</body>
</html>