<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: #dbc1ac;">
<%@ include file="../common/navbar.jsp" %>
	
	<% Product product = (Product) request.getAttribute("product"); %>
	<div class="container">
	<h1>Product Details</h1>
	<% if (product != null) {%>
		<p><strong>Name:</strong> <%= product.getName() %> </p>
		<p><strong>Description:</strong> <%= product.getDescription() %> </p>
		<p><strong>Vendor:</strong> <%= product.getVendor() %> </p>
		<p><strong>urlSlug:</strong> <%= product.getUrlSlug() %> </p>
		<p><strong>Price:</strong> $<%= product.getPrice() %> </p>
		<p><strong>SKU:</strong> <%= product.getSKU() %> </p>
	
	<div>
		<div style="display: inline-block;">
			<% if(staff == null || staff.equals(false)){ %>
    <div class="row">
        <div class="col">
            <form action="/JavaMart/cart/products/<%= product.getUrlSlug() %>" method="post"  >
                <button type="submit" class="btn btn-primary">Add to Cart</button>
            </form>
        	<br/>
            <a href="../products">
                <button type="button" class="btn btn-primary">Back</button>
			</a>
		</div>

		<%} else {%>
					<a href="../edit_product/<%= product.getUrlSlug() %>">
					     <button type="button" class="btn btn-primary">Edit</button>
					    </a>
					  	 <a href="../products">
					        <button type="button" class="btn btn-primary">Back</button>
					    </a>
		<% } %>
		<%} else { %>
		  <p>The slug you entered was not found. 
		  <br/>
		  <a href="../products">
					        <button type="button" class="btn btn-primary">Back</button>
			</a>
		<% } %>
	</div>
	</div>
</body>
</html>