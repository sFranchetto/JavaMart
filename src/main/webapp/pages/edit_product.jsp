<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Product" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
    input, textarea {
        color: grey;
        opacity: 0.75;
    }
</style>
</head>
<body>
	<body>
	<%@ include file="../common/navbar.jsp" %>
	
	<% String slug = (String) request.getAttribute("slug"); %>
	<% String newSlug = slug.substring(1); %>
    <div class="container">
	    <h1>Product Modification</h1>
	    <form action="/JavaMart/products/<%= newSlug %>" method="post">
	        <% Product product = Product.GetProductBySlug(slug); %>
	        <p><strong>Editing:</strong> <%= product.getName() %> </p>
	        <label for="name">Product Name:</label><br>
	        <input type="text" id="name" value=<%= product.getName() %> name="newName"><br>
	        <label for="name">Product Desc:</label><br>
	        <textarea id="name" name="newDesc"><%= product.getDescription() %></textarea><br>
	        <label for="name">Product Vendor:</label><br>
	        <input type="text" id="name" value=<%= product.getVendor() %> name="newVendor"><br>
	        <label for="name">Product Slugs:</label><br>
	        <input type="text" id="name" value=<%= newSlug %> name="newSlug"><br>
	        <label for="name">Product Price:</label><br>
	        <input type="text" id="name" value=<%= product.getPrice() %> name="newPrice"><br>
	        <label for="name">Product SKU:</label><br>
	        <input type="text" id="name" value=<%= product.getSKU() %> name="newSKU"><br>
	        <!-- Add other product fields as needed -->
	        <button type="submit">Modify Product</button>
	    </form>
	</div>
</body>
</html>