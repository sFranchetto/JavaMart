<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome to JavaMart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        /* Remove borders from the table and table cells */
        table, th, td {
            border: none;
        }
    </style>
</head>
<body style="background-color: #dbc1ac;">
    <%@ include file="common/navbar.jsp" %>
    <div class="container text-center my-5">
        <h1>Welcome to JavaMart</h1>
        <p class="mt-3 fs-5">Your one-stop shop for all your tech needs.</p>
        <a href="./products" class="btn btn-primary mt-4">View Product Catalog</a>
    </div>
</body>
</html>
