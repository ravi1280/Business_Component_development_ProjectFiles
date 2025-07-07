package lk.jiat.ee.core.mail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import lk.jiat.ee.core.util.Encryption;

import java.util.Base64;

public class VerificationMail extends Mailable {

    private String to;
    private String verificationCode;

    public VerificationMail( String to, String verificationCode) {
        this.to = to;
        this.verificationCode = verificationCode;
    }

    @Override
    public void build(Message message) throws Exception {
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        message.setSubject("verification Mail");
        String encode = Base64.getEncoder().encodeToString(to.getBytes());
        String link ="http://localhost:8080/J2EE-Example/verify?id="+encode+"&vc="+verificationCode;

//        message.setText("Hello Dev, Your verification code is : " + verificationCode);
        message.setContent(link, "text/html; charset=utf-8");

    }
}
