<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="UserController" method="post">
        <input type="hidden" name="action" value="Login">
        <label>Email:</label>
        <input type="email" name="email" required><br><br>
        <label>Password:</label>
        <input type="password" name="password" required><br><br>
        <button type="submit">Login</button>
    </form>
    <%
        if (request.getAttribute("ERROR") != null) {
    %>
    <p style="color:red;"><%= request.getAttribute("ERROR") %></p>
    <%
        }
    %>
</body>
</html>
