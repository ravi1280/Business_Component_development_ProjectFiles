package lk.jiat.ee.bank.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionSynchronizationRegistry;
import jakarta.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanA {
    @EJB
    private BeanB beanB;

    @Resource
    private UserTransaction userTransaction;

    public void doAction(){
        System.out.println("BeanA doAction");
        System.out.println(userTransaction);
        beanB.doAction();
        doWork();
    }
    public void doWork(){
        System.out.println("BeanA doWork"+userTransaction);
    }
    public void doWork1(){
        System.out.println("BeanA doWork1"+userTransaction);
    }

}
