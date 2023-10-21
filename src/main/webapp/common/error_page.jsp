<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding-top: 100px;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
        }

        h4 {
            color: #555;
            margin-bottom: 20px;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }

        .error-msg {
            color: red;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>You are not supposed to be here!</h1>
        <% String error_msg = request.getAttribute("e").toString(); %>
        <p class="error-msg"><%= error_msg %></p>
        <p>Click <a href="/JavaMart">here</a> to go home.</p>
    </div>
</body>
</html>