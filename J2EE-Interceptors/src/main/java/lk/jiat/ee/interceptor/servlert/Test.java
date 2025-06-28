package lk.jiat.ee.interceptor.servlert;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.interceptor.ejb.UserSessionBean;

import java.io.IOException;


@WebServlet("/test")
public class Test extends HttpServlet {

    @EJB
    private UserSessionBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userBean.doAction();
    }
}
