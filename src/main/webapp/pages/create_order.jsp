<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.Classes.Cart" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JavaMart - Create Order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body style="background-color: #dbc1ac;">
    <jsp:include page="../common/navbar.jsp" />

    <div class="container mt-5">
        <h2>Create Order</h2>
        <form action="./order_made" method="post">
            <div class="mb-3">
                <label for="shippingAddress" class="form-label">Enter Shipping Address:</label>
                <input type="text" class="form-control" id="shippingAddress" name="shippingAddress">
            </div>
            <button type="submit" class="btn btn-primary">Submit Order</button>
        </form>
    </div>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
