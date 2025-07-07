package lk.jiat.ee.core.provider;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import lk.jiat.ee.core.mail.Mailable;
import lk.jiat.ee.core.util.Env;

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
        properties.put("mail.smtp.host", Env.getProperty("mailtrap.host"));
        properties.put("mail.smtp.port", Env.getProperty("mailtrap.port"));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", Env.getProperty("mailtrap.host"));
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
                return new PasswordAuthentication(Env.getProperty("mailtrap.username"), Env.getProperty("mailtrap.password"));
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
