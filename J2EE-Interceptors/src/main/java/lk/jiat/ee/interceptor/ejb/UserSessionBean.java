package lk.jiat.ee.interceptor.ejb;


import jakarta.ejb.Stateless;
import jakarta.interceptor.Interceptors;
import lk.jiat.ee.interceptor.interceptors.TestInterceptor;

@Stateless
@Interceptors({TestInterceptor.class,})
public class UserSessionBean {
    public void doAction(String name) {
        System.out.println("UserSessionBean doAction called : "+name);
    }
}
