package lk.jiat.ee.bank.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.ee.bank.ejb.remote.AccountService;
import lk.jiat.ee.bank.ejb.remote.TransferService;

@Stateless
public class TransferServiceBean implements TransferService {

    @EJB
    private AccountService accountService;

    @Override
    public void transferAmount(String sourceAccountNumber, String destinationAccountNumber, double amount) {

        accountService.debitFromAccount(sourceAccountNumber, amount);
        accountService.creditToAccount(destinationAccountNumber, amount);


    }
}
