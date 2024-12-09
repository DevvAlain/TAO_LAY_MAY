<%-- 
    Document   : browseItems
    Created on : Dec 9, 2024, 8:20:25 AM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="dto.Item"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Browse Items</title>
</head>
<body>
    <h2>Available Items</h2>
    <form action="MainController" method="get">
        <input type="hidden" name="action" value="Search">
        <label>Category:</label>
        <input type="text" name="category">
        <label>Min Price:</label>
        <input type="number" name="minPrice">
        <label>Max Price:</label>
        <input type="number" name="maxPrice">
        <button type="submit">Search</button>
    </form>

    <table border="1">
        <tr>
            <th>Item Name</th>
            <th>Category</th>
            <th>Daily Rate</th>
            <th>Available Quantity</th>
            <th>Actions</th>
        </tr>
        <%
            List<Item> itemList = (List<Item>) request.getAttribute("itemList");
            if (itemList != null) {
                for (Item item : itemList) {
        %>
        <tr>
            <td><%= item.getItemName() %></td>
            <td><%= item.getCategory() %></td>
            <td><%= item.getDailyRate() %></td>
            <td><%= item.getQuantity() %></td>
            <td>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="AddToCart">
                    <input type="hidden" name="itemID" value="<%= item.getItemID() %>">
                    <input type="number" name="quantity" min="1" max="<%= item.getQuantity() %>">
                    <button type="submit">Add to Cart</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
