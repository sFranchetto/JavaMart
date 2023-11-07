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
    <a href="../products">Back home</a>
    <div class="card" style="background-color: #ECE0D1;">
        <div class="card-header">
            <% String slug = (String) request.getAttribute("slug");%>
            <h3>Order Detail on ID: <%= slug %></h3>
        </div>
        <div class="card-body" style="background-color: #ECE0D1;">
            <table class="table" style="background-color: #ECE0D1;">
    <thead>
        <tr>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Order Id</th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Product</th>
            <th scope="col" style="background-color: #ECE0D1;border-bottom: 1px solid #000000;">Quantity</th>
        </tr>
    </thead>
    <tbody>
        <%
          ArrayList<OrderDetail> orderDetail = (ArrayList<OrderDetail>) request.getAttribute("orderdetail");
            if (orderDetail != null && !orderDetail.isEmpty()) {
                for (OrderDetail orderD : orderDetail) {
        %>
        <tr style="border-bottom: 1px solid #000000;">
            <td style="background-color: #ECE0D1;"><h5><strong><%= orderD.getOrderId() %></strong></h5></td>
            <td style="background-color: #ECE0D1;"><%= orderD.getProductName(orderD.getProductId())  %></td>
            <td style="background-color: #ECE0D1;"><%= orderD.getQuantity() %></td>
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
        <%if(staff != null){ %>
            <td style="background-color: #ECE0D1;">
            	<form action="../order/shiporder/<%= slug %>" method="get">
					<button type="submit" class="btn btn-success">Ship Order</button>
				</form>
            </td>
            
        <%}%>
    </tbody>
</table>

        </div>
        
    </div>
</div>

    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>