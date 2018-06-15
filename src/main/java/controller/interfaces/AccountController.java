package controller.interfaces;

import data.DTO.Account;

/**
 * Created by magnus
 */
public interface AccountController {
    Account getAcountByUserId (int userid);
    Account withdraw (int userid, int amount);
    Account deposit (int userid, int amount);
}
