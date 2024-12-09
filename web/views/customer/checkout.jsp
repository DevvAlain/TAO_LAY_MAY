<%-- 
    Document   : checkout
    Created on : Dec 9, 2024, 8:20:38 AM
    Author     : Admin
--%>

<%@page import="dto.CartItem"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
    <h2>Checkout</h2>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="PlaceOrder">
        <label>Rental Days:</label>
        <input type="number" name="rentalDays" min="1" required><br>
        <button type="submit">Place Order</button>
    </form>

    <h3>Cart Items</h3>
    <table border="1">
        <tr>
            <th>Item Name</th>
            <th>Daily Rate</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>
        <%
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart != null) {
                for (CartItem item : cart) {
        %>
        <tr>
            <td><%= item.getItemName() %></td>
            <td><%= item.getDailyRate() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getDailyRate() * item.getQuantity() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>

