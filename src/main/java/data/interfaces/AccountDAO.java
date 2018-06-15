package data.interfaces;

import data.DTO.Account;

/**
 * Created by magnus
 */
public interface AccountDAO {
    void withdraw (Account account, int amount);
    void deposit (Account account, int amount);
}
