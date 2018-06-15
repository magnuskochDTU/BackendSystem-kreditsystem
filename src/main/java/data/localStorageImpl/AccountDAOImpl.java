package data.localStorageImpl;

import data.DTO.Account;
import data.interfaces.AccountDAO;

import javax.ws.rs.NotAcceptableException;

/**
 * Created by magnus
 */
public class AccountDAOImpl implements AccountDAO {

    @Override
    public void withdraw(Account account, int amount) {
        //Ensure that the user have the right amount of money
        if ((account.getBalance() - Math.abs(amount)) >= account.getLimit())
            account.withdraw(amount);
        else
            throw new NotAcceptableException("Account do not have enough money");
    }

    @Override
    public void deposit(Account account, int amount) {
        account.deposit(amount);
    }
}
