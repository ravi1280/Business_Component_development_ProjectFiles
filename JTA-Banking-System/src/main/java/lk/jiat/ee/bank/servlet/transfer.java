package lk.jiat.ee.bank.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.bank.ejb.remote.TransferService;

import java.io.IOException;

@WebServlet("/transfer")
public class transfer extends HttpServlet {
    @EJB
    private TransferService transferService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String sourceAccountNumber= request.getParameter("sourceAccountNumber");
        String destinationAccountNumber= request.getParameter("destinationAccountNumber");
        String amount = request.getParameter("amount");

        transferService.transferAmount(sourceAccountNumber, destinationAccountNumber, Double.parseDouble(amount));

        response.sendRedirect("home.jsp");


    }
}
