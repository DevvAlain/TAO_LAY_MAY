package dao;

import dto.Item;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public List<Item> getItemsByVendor(String vendorID) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM tblItems WHERE vendorID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, vendorID);
        ResultSet rs = stmt.executeQuery();

        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            items.add(new Item(
                rs.getString("itemID"),
                rs.getString("itemName"),
                rs.getString("category"),
                rs.getDouble("dailyRate"),
                rs.getInt("quantity"),
                vendorID
            ));
        }
        conn.close();
        return items;
    }

    public Item getItemByID(String itemID) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM tblItems WHERE itemID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, itemID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Item(
                rs.getString("itemID"),
                rs.getString("itemName"),
                rs.getString("category"),
                rs.getDouble("dailyRate"),
                rs.getInt("quantity"),
                rs.getString("vendorID")
            );
        }
        conn.close();
        return null;
    }

    public void addItem(Item item) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO tblItems (itemID, itemName, category, dailyRate, quantity, vendorID) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, item.getItemID());
        stmt.setString(2, item.getItemName());
        stmt.setString(3, item.getCategory());
        stmt.setDouble(4, item.getDailyRate());
        stmt.setInt(5, item.getQuantity());
        stmt.setString(6, item.getVendorID());
        stmt.executeUpdate();
        conn.close();
    }

    public void updateItem(Item item) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE tblItems SET itemName = ?, category = ?, dailyRate = ?, quantity = ? WHERE itemID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, item.getItemName());
        stmt.setString(2, item.getCategory());
        stmt.setDouble(3, item.getDailyRate());
        stmt.setInt(4, item.getQuantity());
        stmt.setString(5, item.getItemID());
        stmt.executeUpdate();
        conn.close();
    }

    public void deleteItem(String itemID) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "DELETE FROM tblItems WHERE itemID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, itemID);
        stmt.executeUpdate();
        conn.close();
    }
}
