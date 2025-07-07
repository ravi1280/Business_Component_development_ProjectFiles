package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.service.ProductService;

import java.io.IOException;

@WebServlet("/admin/delete_product")
public class DeleteProduct extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String pid = req.getParameter("pid");
       productService.deleteProduct(Long.parseLong(pid));

       resp.sendRedirect(req.getContextPath()+"/admin");
    }
}
