package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MainController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "views/login.jsp"; // Default page

        try {
            switch (action) {
                case "Login":
                    url = "/UserController";
                    break;
                case "Logout":
                    url = "views/logout.jsp";
                    break;
                case "BrowseItems":
                case "Search":
                    url = "ItemController";
                    break;
                case "AddToCart":
                case "Checkout":
                case "PlaceOrder":
                    url = "OrderController";
                    break;
                case "ManageItems":
                case "EditItem":
                case "CreateItem":
                case "DeleteItem":
                    url = "ItemController";
                    break;
                case "ManageUsers":
                case "InspectReturns":
                case "TopRentedItems":
                    url = "AdminController";
                    break;
                default:
                    request.setAttribute("ERROR", "Invalid action.");
            }
        } catch (Exception e) {
            log("Error in MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
