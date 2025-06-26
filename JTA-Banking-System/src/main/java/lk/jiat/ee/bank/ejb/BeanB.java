package lk.jiat.ee.bank.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.transaction.TransactionSynchronizationRegistry;

@Stateless
public class BeanB {

    public void doAction(){
        System.out.println("BeanB doAction");

    }
}
