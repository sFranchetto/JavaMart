<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Classes.Product" %>
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
	
	<div class="container">
    <h1>Product Showcase</h1>
    <% List<Product> products = ProductFactory.returnAllProducts(); %>
    <div class="product-container">
	    <%
	    int count = 0; // Initialize a count variable
	    for (Product product : products) {
	        if (count % 3 == 0) {
	    %>
		    <div class="row">
		    <% } %>
		        <div class="col-md-4">
		            <div class="card mb-4">
		                <img src="..." class="card-img-top" alt="...">
		                <div class="card-body">
		                    <strong><%= product.getName() %></strong><br/>
		                    <%= product.getDescription() %><br/>
		                    Price: <%= product.getPrice() %><br/>
		                    <form action="./cart/products/<%= product.getUrlSlug() %>" method="post">
			                    <button type="submit" class="btn btn-primary">Add to Cart</button>
			                 </form>
		                    <a href="./products/<%= product.getUrlSlug() %>">
		                    	<button type="button" class="btn btn-primary">Details</button>
		                    </a>
		                </div>
		            </div>
		     	</div>
		    <%
		    count++;
		    if (count % 3 == 0) {
		    %>
    		</div>
    <% } %>
    <% } %>
</div>
</div>
</body>
</html>