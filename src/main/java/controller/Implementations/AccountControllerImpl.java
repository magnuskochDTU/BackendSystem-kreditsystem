package controller.Implementations;

import controller.ControllerRegistry;
import controller.interfaces.AccountController;
import controller.interfaces.CustomerController;
import data.DTO.Account;
import data.interfaces.AccountDAO;
import data.localStorageImpl.AccountDAOImpl;

/**
 * Created by magnus
 */
public class AccountControllerImpl implements AccountController {
    private AccountDAO accountDAO = new AccountDAOImpl();
    private CustomerController customerController = ControllerRegistry.getCustomerController();

    @Override
    public Account getAcountByUserId(int userid) {
        return customerController.getUserById(userid).getCustomer().getAccount();
    }

    @Override
    public Account withdraw(int userid, int amount) {
        Account account = getAcountByUserId(userid);
        accountDAO.withdraw(account, amount);
        return account;
    }

    @Override
    public Account deposit(int userid, int amount) {
        Account account = getAcountByUserId(userid);
        accountDAO.deposit(account, amount);
        return account;
    }
}
