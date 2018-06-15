package controller.Implementations;

import controller.interfaces.CustomerController;
import data.DTO.User;
import data.DTO.UserUpdate;
import data.interfaces.CustomerDAO;
import data.localStorageImpl.CustomerDAOImpl;

import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by magnus
 */
public class CustomerControllerImpl implements CustomerController {
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<User> getAllUsers() {
        return customerDAO.getAll();
    }

    @Override
    public User getUserById(int id) {
        return customerDAO.getById(id);
    }

    @Override
    public User addUser(User user) {
         return customerDAO.add(user);
    }

    @Override
    public User updateUser(UserUpdate userUpdate, int userid) {
        User customer = getUserById(userid);
        customer.setFirstname(userUpdate.getFirstname());
        customer.setLastname(userUpdate.getLastname());
        customer.getCustomer().setAddress(userUpdate.getCustomer().getAddress());
        customer.getCustomer().setPhonenumber(userUpdate.getCustomer().getPhonenumber());
        customer.getCustomer().setMail(userUpdate.getCustomer().getMail());
        return customerDAO.update(customer);
    }

    @Override
    public User removeUser(int id) {
        if (getUserById(id) != null)
            return customerDAO.delete(id);

        else
            throw new NotFoundException("Could not find a user with that id");

    }
}
