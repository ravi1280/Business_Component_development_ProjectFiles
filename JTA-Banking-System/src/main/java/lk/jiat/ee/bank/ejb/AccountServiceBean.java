package lk.jiat.ee.bank.ejb;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.bank.ejb.remote.AccountService;
import lk.jiat.ee.bank.entity.Account;


@Stateless
public class AccountServiceBean implements AccountService {
    @PersistenceContext
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void creditToAccount(String accountNumber, double amount) {
        try {
            Account account = em.createNamedQuery("Account.findByAccountNo", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
            if (amount > 0) {
                account.setBalance(account.getBalance() + amount);
            }
            em.merge(account);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void debitFromAccount(String accountNumber, double amount) {
        try {
            Account account = em.createNamedQuery("Account.findByAccountNo", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
            if (account.getBalance() >= 0) {
                account.setBalance(account.getBalance() - amount);
                em.merge(account);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }
}
