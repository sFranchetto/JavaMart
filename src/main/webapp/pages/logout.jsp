<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Logout</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f2f2f2;
            padding-top: 100px;
        }

        h1 {
            color: #333;
            font-size: 36px;
            margin-bottom: 20px;
        }

        h2 {
            color: #555;
            font-size: 24px;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <%
        //Spaghetti code
        session.setAttribute("isStaff", false);
        session.invalidate();
    %>
    <h1>You have logged out</h1>
    <h2>Click <a href="/JavaMart/products">here</a> to return to the product catalog.</h2>
</body>
</html>
