package lk.jiat.ee.servlet;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.ejb.UserSessionBean;

import java.io.IOException;
import java.io.PrintWriter;

@DeclareRoles({"USER"})
@ServletSecurity(@HttpConstraint(rolesAllowed = "USER") )
@WebServlet("/user")
public class User extends HttpServlet {
    @EJB
    private UserSessionBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("This is User Page");
        userBean.method4();


    }
}
