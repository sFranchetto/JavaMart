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
<title>JavaMart</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body style="background-color: #dbc1ac;">
<%@ include file="../common/navbar.jsp" %>
    <div class="container mt-5" >
    <a href="./products">Back to catalog</a>
    <div class="card" style="background-color: #ECE0D1;">
        <div class="card-header">
            <h3>Your Cart</h3>
        </div>
        <div class="card-body" style="background-color: #ECE0D1;">
            <table class="table" style="background-color: #ECE0D1;">
                <thead >
                    <tr>
                        <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;"> </th >
                        <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Price</th>
                        <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Quantity </th>
                        <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;"> </th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    ArrayList<Product> cart = (ArrayList<Product>) request.getAttribute("cart");
                    if (cart != null && !cart.isEmpty()) {
                        for (Product product : cart) {
                    %>
                    <tr style="border-bottom: 1px solid #000000;">
                        
                        <td style="background-color: #ECE0D1;"><h5><strong><%= product.getName() %></strong></h5>
                        <%= product.getDescription() %>
                        </td>
                        <td style="background-color: #ECE0D1;">$<%= product.getPrice() %></td>
                        <td style="background-color: #ECE0D1;">
                        	<p>Quantity here</p>
                        </td>
                        <td style="background-color: #ECE0D1;">
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
                        <td colspan="3" style="background-color: #ECE0D1;">Cart is empty</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <div class="card-footer text-muted d-flex justify-content-between align-items-center">
            <%
				double total = 0;
				if (cart != null && !cart.isEmpty()) {
				    for (Product product : cart) {
				        total += product.getPrice();
				    }
				}
			%>
            Total: $<%= String.format("%.2f", total) %>
            <button class="btn btn-primary">
  				Place Order
  			</button>
        </div>
    </div>
</div>

    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>