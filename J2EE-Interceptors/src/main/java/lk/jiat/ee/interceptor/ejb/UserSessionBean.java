package lk.jiat.ee.interceptor.ejb;


import jakarta.ejb.Stateless;

@Stateless
public class UserSessionBean {
    public void doAction() {

        System.out.println("UserSessionBean doAction called");
    }
}
