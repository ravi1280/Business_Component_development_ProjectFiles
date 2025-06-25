package lk.jiat.ee.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.bank.ejb.remote.RegisterService;

import java.io.IOException;

@WebServlet("/register")
public class register extends HttpServlet {

    @EJB
    private RegisterService registerService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String name =request.getParameter("name");
         String email =request.getParameter("email");
         String password =request.getParameter("password");

         registerService.registerUser(name, email, password);
         response.sendRedirect("index.jsp");
    }

}
