<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .bg-custom-brown {
            background-color: #964b00; /* Replace with your desired color code */
        }
    </style>
</head>
<body>
    <% 
	    Boolean staffObj = (Boolean) session.getAttribute("isStaff");
	    Boolean customerObj = (Boolean) session.getAttribute("isCustomer");
	    Object passcode = session.getAttribute("passcode");
	
	    boolean staff = staffObj != null && staffObj.booleanValue();
	    boolean customer = customerObj != null && customerObj.booleanValue();
    %>

    <nav class="navbar navbar-expand-lg bg-brown">
        <div class="container-fluid">
            <a class="navbar-brand" href="/JavaMart">JavaMart</a>
            
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/JavaMart/products">Catalog</a>
                    </li>
                    <% if(customer){ %>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/JavaMart/orders">My Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/JavaMart/claim_order">Claim Order</a>
                        </li>
                    <%} else if(staff){ %>
                        <a class="nav-link active" aria-current="page" href="/JavaMart/orders?method=staff_orders">View Orders</a>
                        <a class="nav-link active" aria-current="page" href="/JavaMart/privileges">Edit Privileges</a>
                    <%} %>
                </ul>
            </div>
            <div class ="ml-auto">
                <% if(!staff){ %>
                <%} else {%>
                    <a class="nav-link pe-3" href="./create_product">
                        <button type="button" class="btn btn-secondary">Create product</button>
                    </a>
                <%} %>
            </div>
            <div class="ml-auto" style="display: flex;">
                <% if(!staff && !customer) { %>
                    <a class="nav-link pe-3" href="/JavaMart/cart">
                        <button type="button" class="btn btn-warning"> My Cart </button>
                    </a>
                    <a class="nav-link pe-3" href="/JavaMart/login">
                        You are not currently logged in.
                        <button type="button" class="btn btn-success">Login</button>
                    </a>
                <% } else if (staff) { %>
                    <form action="logout" method="post">
                        <a class="nav-link pe-3" href="/JavaMart/logout">
                            <div class="d-flex align-items-center">
                                <div class="nav-link">
                                    You are currently logged in as Staff with passcode <strong><%= passcode %>. </strong>
                                    <a href="./change_passcode"> Change passcode</a>
                                </div>
                                <button type="submit" class="btn btn-danger me-3"> Log out</button>
                            </div>
                        </a>
                    </form>
                <% } else { %>
                    <form action="logout" method="post" class="d-flex align-items-center">
                        <a class="nav-link pe-3" href="/JavaMart/cart">
                            <button type="button" class="btn btn-warning me-3"> My Cart </button>
                        </a>
                        <div class="nav-link">
                            You are currently logged in as Customer with passcode <strong><%= passcode %>. </strong>
                            <a href="./change_passcode"> Change passcode</a>
                        </div>
                        <a class="nav-link pe-3" href="/JavaMart/logout">
                            <button type="submit" class="btn btn-danger"> Log out</button>
                        </a>
                    </form>
                <% } %>
            </div>
        </div>
    </nav>
    <hr style="background-color: black; height: 2px; margin: 0;">
</body>
</html>
