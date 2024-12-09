package dao;

import dto.CartItem;
import util.DatabaseConnection;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.List;

public class OrderDAO {
    public void placeOrder(HttpSession session, String customerID, int rentalDays) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        String query = "INSERT INTO tblOrders (customerID, orderDate, returnDate, status) VALUES (?, GETDATE(), DATEADD(DAY, ?, GETDATE()), 'Pending')";
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, customerID);
        stmt.setInt(2, rentalDays);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        int orderID = rs.getInt(1);

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        for (CartItem item : cart) {
            query = "INSERT INTO tblOrderDetails (orderID, itemID, quantity, dailyRate) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderID);
            stmt.setString(2, item.getItemID());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getDailyRate());
            stmt.executeUpdate();
        }

        conn.commit();
        conn.close();
    }
}
