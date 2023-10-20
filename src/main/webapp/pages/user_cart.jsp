<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.Classes.Cart" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap Cart Template</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<%@ include file="../common/navbar.jsp" %>
    <div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>Your Cart</h3>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col"> </th>
                        <th scope="col">Price</th>
                        <th scope="col"> </th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Use JSP code to dynamically generate the table rows here -->
                    <%
                    ArrayList<Product> cart = (ArrayList<Product>) request.getAttribute("cart");
                    if (cart != null && !cart.isEmpty()) {
                        for (Product product : cart) {
                    %>
                    <tr>
                        <td><h5><strong><%= product.getName() %></strong></h5>
                        <%= product.getDescription() %>
                        </td>
                        <td><%= product.getPrice() %></td>
                        <td>
                        	<form action="./cart/products/<%= product.getUrlSlug() %>" method="post">
							    <input type="hidden" name="_method" value="delete">
							    <button type="submit" class="btn btn-danger">Remove</button>
							</form>
                        </td>
                    </tr>
                    <% 
                        } 
                    } else {
                    %>
                    <tr>
                        <td colspan="3">Cart is empty</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <div class="card-footer text-muted">
            Total: $100
        </div>
    </div>
</div>

    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>