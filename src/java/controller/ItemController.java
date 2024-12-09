package controller;

import dao.ItemDAO;
import dto.Item;
import dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        ItemDAO itemDAO = new ItemDAO();

        try {
            switch (action) {
                case "ManageItems":
                    HttpSession session = request.getSession();
                    String vendorID = ((User) session.getAttribute("user")).getUserID();
                    request.setAttribute("itemList", itemDAO.getItemsByVendor(vendorID));
                    request.getRequestDispatcher("views/vendor/manageItems.jsp").forward(request, response);
                    break;

                case "EditItem":
                    String itemID = request.getParameter("itemID");
                    Item item = itemDAO.getItemByID(itemID);
                    request.setAttribute("item", item);
                    request.getRequestDispatcher("views/vendor/editItem.jsp").forward(request, response);
                    break;

                case "DeleteItem":
                    itemID = request.getParameter("itemID");
                    itemDAO.deleteItem(itemID);
                    response.sendRedirect("MainController?action=ManageItems");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Item operation failed.");
            request.getRequestDispatcher("views/vendor/manageItems.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        ItemDAO itemDAO = new ItemDAO();

        try {
            if ("CreateItem".equals(action)) {
                Item newItem = new Item(
                    request.getParameter("itemID"),
                    request.getParameter("itemName"),
                    request.getParameter("category"),
                    Double.parseDouble(request.getParameter("dailyRate")),
                    Integer.parseInt(request.getParameter("quantity")),
                    request.getParameter("vendorID")
                );
                itemDAO.addItem(newItem);
                response.sendRedirect("MainController?action=ManageItems");
            } else if ("UpdateItem".equals(action)) {
                Item updatedItem = new Item(
                    request.getParameter("itemID"),
                    request.getParameter("itemName"),
                    request.getParameter("category"),
                    Double.parseDouble(request.getParameter("dailyRate")),
                    Integer.parseInt(request.getParameter("quantity")),
                    request.getParameter("vendorID")
                );
                itemDAO.updateItem(updatedItem);
                response.sendRedirect("MainController?action=ManageItems");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Item update failed.");
            request.getRequestDispatcher("views/vendor/manageItems.jsp").forward(request, response);
        }
    }
}
