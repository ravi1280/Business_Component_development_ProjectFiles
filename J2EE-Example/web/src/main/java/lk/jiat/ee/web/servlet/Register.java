package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.mail.VerificationMail;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.core.provider.MailServiceProvider;
import lk.jiat.ee.core.service.UserService;
import lk.jiat.ee.core.util.Encryption;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/register")
public class Register extends HttpServlet {

    @EJB
   private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");

        String encryptPassword = Encryption.encrypt(password);

        User user = new User(name, contact, email, encryptPassword);

        String verificationCode = UUID.randomUUID().toString();
        System.out.println(verificationCode);
        VerificationMail verificationMail = new VerificationMail(email, verificationCode);
        System.out.println(verificationMail);
        MailServiceProvider.getInstance().sendMail(verificationMail);

        userService.createUser(user);

        response.sendRedirect(request.getContextPath()+"/login.jsp");

    }
}
