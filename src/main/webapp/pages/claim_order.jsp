<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Ship Order</title>
</head>
<body style="background-color: #dbc1ac;">
<%@ include file="../common/navbar.jsp" %>
    <div class="container mt-5">
	    <% if (session.getAttribute("failureMessage_claim") != null) { %>
		    <div id="error-message" class="alert alert-danger" role="alert">
		        <strong>Error:</strong> <%= session.getAttribute("failureMessage_claim") %>
		    </div>
		
		    <script>
		        // JavaScript code to hide the error message after 5 seconds
		        setTimeout(function() {
		            document.getElementById('error-message').style.display = 'none';
		            <% session.removeAttribute("failureMessage_claim"); %>
		        }, 5000);
		    </script>
		<% } %>

        <h2>Claim Order here</h2>
        <form action="/JavaMart/claim_order" method="post"> 
            <div class="mb-3">
                <label for="productName" class="form-label">Enter the order ID, please only enter the digit(s)</label>
                <input type="text" class="form-control" id="orderNum" name="orderNum" required>
            </div>
            <button type="submit" class="mt-2 btn btn-success">Claim Order</button>
            <button onclick="window.location.href='/JavaMart/products';" class="mt-2 btn btn-danger">Cancel</button>
        </form>
    </div>
</body>
</html>
