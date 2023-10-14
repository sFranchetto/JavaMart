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
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
	
	<% Product product = (Product) request.getAttribute("product"); %>
	<div class="container">
	<h1>Product Details</h1>
	<% //if (product != null) {%>
	<p><strong>Name:</strong> <%= product.getName() %> </p>
	<p><strong>Description:</strong> <%= product.getDescription() %> </p>
	<p><strong>Vendor:</strong> <%= product.getVendor() %> </p>
	<p><strong>urlSlug:</strong> <%= product.getUrlSlug() %> </p>
	<p><strong>Price:</strong> $<%= product.getPrice() %> </p>
	<p><strong>SKU:</strong> <%= product.getSKU() %> </p>
	<%// } else { %>
		<!--  <p>The slug you entered was not found. -->
	<%// } %>
	<% if(staff == null || staff.equals(false)){ %>
			
	<%} else {%>
		<a href="../edit_product/<%= product.getUrlSlug() %>">
			<button type="button" class="btn btn-primary">Edit</button>
		</a>
	<% } %>
	<a href="../products">
		<button type="button" class="btn btn-primary">Back</button>
	</a>
	</div>
</body>
</html>