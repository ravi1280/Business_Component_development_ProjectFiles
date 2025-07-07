package lk.jiat.ee.core.mail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lk.jiat.ee.core.provider.MailServiceProvider;
import lk.jiat.ee.core.util.Env;

public abstract class Mailable implements Runnable {
    private MailServiceProvider mailserviceprovider;

    public Mailable() {
        mailserviceprovider = MailServiceProvider.getInstance();
    }
    @Override
    public void run() {
        try {
            Session session = Session.getInstance(mailserviceprovider.getProperties(),mailserviceprovider.getAuthenticator());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Env.getProperty("app.email")));
            build(message);
            Transport.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public abstract void build(Message message) throws Exception;
}
