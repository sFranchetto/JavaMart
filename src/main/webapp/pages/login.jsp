<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>JavaMart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
 	<style>
        table, th, td {
            border: none;
        }
    </style>
 </head>
<body style="background-color: #dbc1ac;">
<%@ include file="../common/navbar.jsp" %>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header" style="background-color: #ECE0D1;">Staff Login</div>
                <div class="card-body" style="background-color: #ECE0D1;">
                    <form action="login" method="post">
                        <div class="mb-3">
                            <label for="password" class="form-label">Enter Passcode</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>
                    <% 
                    String incorrectMessage = (String) request.getAttribute("message");
                    if(incorrectMessage != null) {
                    %>
                    <div class="alert alert-danger mt-3" role="alert">
                        <%= incorrectMessage %>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
