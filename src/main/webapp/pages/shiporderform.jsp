<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Ship Order</title>
</head>
<body style="background-color: #dbc1ac;">
<%@ include file="../common/navbar.jsp" %>
    <% String slug = (String) request.getAttribute("orderNum"); %>
    <div class="container mt-5">
        <h2>Ship Order on order number: <%= slug %></h2>
        <form action="/JavaMart/order/shiporder/<%= slug %>" method="post"> 
            <div class="mb-3">
                <label for="productName" class="form-label">Enter tracking number</label>
                <input type="text" class="form-control" id="trackingNum" name="trackingNum" required>
            </div>
            <button type="submit" class="mt-2 btn btn-success">Ship Order</button>
            <button onclick="window.history.back();" class="mt-2 btn btn-danger">Cancel</button>
        </form>
    </div>
</body>
</html>
