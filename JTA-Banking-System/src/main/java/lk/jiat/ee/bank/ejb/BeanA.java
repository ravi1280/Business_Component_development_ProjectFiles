package lk.jiat.ee.bank.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.TransactionSynchronizationRegistry;

@Stateless
public class BeanA {
    @EJB
    private BeanB beanB;



    public void doAction(){
        System.out.println("BeanA doAction");

        beanB.doAction();
    }
}
