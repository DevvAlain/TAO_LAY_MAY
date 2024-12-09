package dto;

public class Order {
    private String orderID;
    private String customerID;
    private String orderDate;
    private String returnDate;
    private String status;

    public Order(String orderID, String customerID, String orderDate, String returnDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters and Setters

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
