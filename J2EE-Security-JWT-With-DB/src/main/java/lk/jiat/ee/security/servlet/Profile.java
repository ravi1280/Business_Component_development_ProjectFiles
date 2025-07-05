package lk.jiat.ee.security.servlet;

import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@DeclareRoles({"USER","ADMIN"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN","USER"}))
@WebServlet("/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().write("<h1>Profile Page</h1>");
    }
}
