<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
    <h2>Edit Item</h2>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="UpdateItem">
        <input type="hidden" name="itemID" value="<%= request.getAttribute("itemID") %>">

        <label>Item Name:</label>
        <input type="text" name="itemName" value="<%= request.getAttribute("itemName") %>" required><br>

        <label>Category:</label>
        <input type="text" name="category" value="<%= request.getAttribute("category") %>" required><br>

        <label>Daily Rate:</label>
        <input type="number" name="dailyRate" value="<%= request.getAttribute("dailyRate") %>" step="0.01" required><br>

        <label>Quantity:</label>
        <input type="number" name="quantity" value="<%= request.getAttribute("quantity") %>" required><br>

        <button type="submit">Update</button>
    </form>
</body>
</html>
