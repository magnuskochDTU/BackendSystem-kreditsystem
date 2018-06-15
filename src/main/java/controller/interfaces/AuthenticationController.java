package controller.interfaces;

import data.DTO.LoginDetails;
import data.DTO.Role;

/**
 * Created by magnus
 */
public interface AuthenticationController {
    String login (LoginDetails loginDetails);
    Role getRole (int userid);
}
