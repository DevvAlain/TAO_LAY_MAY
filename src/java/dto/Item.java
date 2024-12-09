package dto;

public class Item {
    private String itemID;
    private String itemName;
    private String category;
    private double dailyRate;
    private int quantity;
    private String vendorID;

    public Item(String itemID, String itemName, String category, double dailyRate, int quantity, String vendorID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.category = category;
        this.dailyRate = dailyRate;
        this.quantity = quantity;
        this.vendorID = vendorID;
    }

    // Getters and Setters

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }
}
