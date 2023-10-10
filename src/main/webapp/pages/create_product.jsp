<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Product" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
 	<style>
        /* Remove borders from the table and table cells */
        table, th, td {
            border: none;
        }
    </style>
 </head>
<body>
	<%@ include file="../common/navbar.jsp" %>
	<div class="container mt-5">
    <h2>Create Product</h2>
    <form action="create_product" method="post"> 
        <div class="mb-3">
            <label for="productName" class="form-label">Product Name</label>
            <input type="text" class="form-control" id="productName" name="productName" required>
        </div>
        <div class="mb-3">
            <label for="productSKU" class="form-label">Product SKU</label>
            <input type="text" class="form-control" id="productSKU" name="productSKU" placeholder="SKU-000" required>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>
</body>
</html>