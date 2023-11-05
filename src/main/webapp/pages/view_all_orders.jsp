<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.Classes.Cart" %>
<%@ page import="com.JavaMart.Classes.OrderDetail" %>
<%@ page import="com.JavaMart.Classes.Order" %>
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
    <a href="../orders">Back to Orders</a>
    <div class="card" style="background-color: #ECE0D1;">
        <div class="card-header">
            <h3>Orders for all Users</h3>
        </div>
        <div class="card-body" style="background-color: #ECE0D1;">
            <table class="table" style="background-color: #ECE0D1;">
    <thead>
        <tr>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Order Id</th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">User</th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Shipping Address</th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Shipped</th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Tracking Number</th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;"></th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;"></th>
        </tr>
    </thead>
    <tbody>
        <%
          ArrayList<Order> allOrders = (ArrayList<Order>) request.getAttribute("allOrders");
            if (allOrders != null && !allOrders.isEmpty()) {
                for (Order orders : allOrders) {
        %>
        <tr style="border-bottom: 1px solid #000000;">
            <td style="background-color: #ECE0D1;"><h5><strong><%= orders.getId() %></strong></h5></td>
            <td style="background-color: #ECE0D1;"><%= orders.getUser()  %></td>
            <td style="background-color: #ECE0D1;"><%= orders.getShippingAddress() %></td>
            <td style="background-color: #ECE0D1;"><%= orders.getIsShipped() %></td>
            <td style="background-color: #ECE0D1;"><%= orders.getTrackingNum() %></td>
            <td style="background-color: #ECE0D1;">
			<form action="./orders/<%=orders.getId() %>" method="get">
				<button type="submit" class="btn btn-primary">Details</button>
			</form>
            </td>
            <td style="background-color: #ECE0D1;">
            	<form action="./" method="post">
					<input type="hidden" name="_method" value="delete">
					<button type="submit" class="btn btn-success">Ship Order</button>
				</form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3" style="background-color: #ECE0D1;">No order details available</td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>

        </div>
        
    </div>
</div>

    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>