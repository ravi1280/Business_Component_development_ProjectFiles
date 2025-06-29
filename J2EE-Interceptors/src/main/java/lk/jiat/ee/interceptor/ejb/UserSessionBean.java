package lk.jiat.ee.interceptor.ejb;


import jakarta.ejb.Stateless;
import jakarta.interceptor.Interceptors;
import lk.jiat.ee.interceptor.annotation.Login;
import lk.jiat.ee.interceptor.interceptors.AInterceptor;
import lk.jiat.ee.interceptor.interceptors.TestInterceptor;

@Stateless
//@Interceptors({TestInterceptor.class, AInterceptor.class})

public class UserSessionBean {
    @Login
    public String doAction(String name) {
        System.out.println(" doAction Start");
        System.out.println("UserSessionBean doAction called : "+name);
        System.out.println(" doAction End");
        return name;
    }
}
