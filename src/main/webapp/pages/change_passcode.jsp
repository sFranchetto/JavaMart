<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.JavaMart.Classes.Product" %>
<%@ page import="com.JavaMart.Classes.Cart" %>
<%@ page import="com.JavaMart.ProductFactory" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JavaMart - Change Passcode</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body style="background-color: #dbc1ac;">

    <%@ include file="../common/navbar.jsp" %>

    <div class="container mt-5">
        <%-- Display error message if it exists --%>
        <% if (session.getAttribute("failureMessage") != null) { %>
            <div class="alert alert-danger" role="alert">
                <strong>Error:</strong> <%= session.getAttribute("failureMessage") %>
            </div>
        <% } %>

        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header" style="background-color: #ECE0D1;"><h3><strong>Change Passcode</strong>: <u>Current Passcode:</u> <strong><%= passcode %> </strong></h3></div>
                    <div class="card-body" style="background-color: #ECE0D1;">
                        <form action="./change_passcode" method="post">
                            <div class="mb-3">
                                <label for="passcode" class="form-label">Enter New Passcode</label>
                                <input type="password" class="form-control" id="passcode" name="passcode" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Change Passcode</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
