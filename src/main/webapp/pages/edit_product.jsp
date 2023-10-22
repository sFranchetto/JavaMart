<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JavaMart</title>
<style>
    input, textarea {
        color: grey;
        opacity: 0.75;
    }
</style>
</head>
<body style="background-color: #dbc1ac;">
	<%@ include file="../common/navbar.jsp" %>
	
	<% String slug = (String) request.getAttribute("slug"); %>
	<% String newSlug = slug.substring(1); %>
    <div class="container">
	    <h1>Product Modification</h1>
	    <form action="/JavaMart/products/<%= newSlug %>" method="post">
	        <% Product product = Product.GetProductBySlug(slug); %>
	        <% Product productNew = Product.GetProduct(product.getSKU()); %>
	        <p><strong>Editing: <%= productNew.getName() %></strong></p>
	        <strong><label for="name">Product Name:</label></strong><br>
	        <input type="text" id="name" value=<%= product.getName() %> name="newName"><br>
	        <strong><label for="name">Product Desc:</label></strong><br>
	        <textarea id="name" name="newDesc"><%= product.getDescription() %></textarea><br>
	        <strong><label for="name">Product Vendor:</label></strong><br>
	        <input type="text" id="name" value=<%= product.getVendor() %> name="newVendor"><br>
	        <strong><label for="name">Product Slugs:</label></strong><br>
	        <input type="text" id="name" value=<%= newSlug %> name="newSlug"><br>
	        <strong><label for="name">Product Price:</label></strong><br>
	        <input type="text" id="name" value=<%= product.getPrice() %> name="newPrice"><br>
	        <strong><label for="name">Product SKU:</label></strong><br>
	        <input type="text" id="name" value=<%= product.getSKU() %> name="newSKU"><br>
	        <br/>
	        <button type="submit" class="btn btn-secondary">Modify Product</button>
	    </form>
	</div>
</body>
</html>