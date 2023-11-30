<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JavaMart.Classes.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body style="background-color: #dbc1ac;">

<%@ include file="../common/navbar.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h2>All Users</h2>
            <table class="table" style="background-color: #ECE0D1;">
                <thead>
                <tr style="border-bottom: 1px solid #000000; background-color: #B1A193;">
                    <th scope="col">ID</th>
                    <th scope="col">Passcode</th>
                    <th scope="col">User Type</th>
                    <th scope="col">Change Privilege</th>
                </tr>
                </thead>
                <tbody>
                    <% List<User> users = (List<User>)request.getAttribute("users");
                       for (User user : users) { %>
                        <tr style="border-bottom: 1px solid #000000;">
                            <td><%= user.getId() %></td>
                            <td><%= user.getPasscode() %></td>
                            <td><%= user.getUserType() %></td>
                            <td>
                                <form action="./privileges" method="post">
                                    <input type="hidden" name="userId" value="<%= user.getId() %>">
                                    <button type="submit" class="btn btn-info">Change Privilege</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>

</html>
