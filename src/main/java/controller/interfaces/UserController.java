package controller.interfaces;

import data.DTO.User;
import data.DTO.UserUpdate;

import java.util.List;

/**
 * Created by magnus
 */
public interface UserController {
    List<User> getAllUsers ();
    User getUserById (int id);
    User addUser (User user);
    User updateUser (UserUpdate user, int id);
    User removeUser (int id);
}
