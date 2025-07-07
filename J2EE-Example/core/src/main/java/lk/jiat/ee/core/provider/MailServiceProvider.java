package lk.jiat.ee.core.provider;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import lk.jiat.ee.core.mail.Mailable;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MailServiceProvider {

    private Properties properties = new Properties();
    private Authenticator authenticator;
    private static MailServiceProvider instance;
    private ThreadPoolExecutor executor;
    private BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();

    private MailServiceProvider(){
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "2525");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "false");
    }

    public static MailServiceProvider getInstance(){
        if(instance == null){
            instance = new MailServiceProvider();
        }
        return instance;
    }

    public void start() {
        authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("395b0f4b8ddfb5", "107268a8d8f549");
            }
        };
        executor = new ThreadPoolExecutor(
                5,
                10,
                5,
                TimeUnit.SECONDS,
                blockingQueue,
                new ThreadPoolExecutor.AbortPolicy());

        executor.prestartAllCoreThreads();

        System.out.println("Mail service provider started");
    }

    public void sendMail(Mailable mailable) {
        System.out.println("Mail service provider sending mail"+mailable);
        blockingQueue.offer(mailable);

    }

    public Properties getProperties() {

        return properties;
    }
    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void shutdown() {
        if(executor != null){
            executor.shutdown();
        }
    }
}
