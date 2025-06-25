package lk.jiat.ee.bank.ejb;

import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import lk.jiat.ee.bank.ejb.remote.AccountService;
import lk.jiat.ee.bank.ejb.remote.TransferService;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransferServiceBean implements TransferService {
    @EJB
    private AccountService accountService;

    @Inject
    private UserTransaction userTransaction;
    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void transferAmount(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        try {
            userTransaction.begin();
            accountService.debitFromAccount(sourceAccountNumber, amount);
            accountService.creditToAccount(destinationAccountNumber, amount);
            userTransaction.commit();
        } catch (NotSupportedException e) {
            try {
                userTransaction.rollback();
            } catch (SystemException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (HeuristicRollbackException e) {
            throw new RuntimeException(e);
        } catch (HeuristicMixedException e) {
            throw new RuntimeException(e);
        } catch (RollbackException e) {
            throw new RuntimeException(e);
        }

    }
}
