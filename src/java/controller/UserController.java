package controller;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/UserController")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("Login".equals(action)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserDAO userDAO = new UserDAO();
            User user = userDAO.authenticate(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                switch (user.getRole().toUpperCase()) {
                    case "ADMIN":
                        response.sendRedirect("views/admin/manageUsers.jsp");
                        break;
                    case "VENDOR":
                        response.sendRedirect("views/vendor/manageItems.jsp");
                        break;
                    case "CUSTOMER":
                        response.sendRedirect("views/customer/browseItems.jsp");
                        break;
                    default:
                        response.sendRedirect("views/login.jsp");
                }
            } else {
                request.setAttribute("ERROR", "Invalid credentials!");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
            }
        } else if ("Logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("views/login.jsp");
        }
    }
}
