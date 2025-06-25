package lk.jiat.ee.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.ee.bank.ejb.remote.LoginService;

import java.io.IOException;

@WebServlet("/login")
public class login extends HttpServlet {
    @EJB
    private LoginService loginService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =request.getParameter("email");
        String password =request.getParameter("password");
      boolean login =  loginService.loginUser(email, password);
      if (login) {
          HttpSession session = request.getSession();
          session.setAttribute("user", email);
          response.sendRedirect("home.jsp");
      }else {
          response.sendRedirect("login.jsp");
      }
    }
}
