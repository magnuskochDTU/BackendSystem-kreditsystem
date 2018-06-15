package controller.Implementations;

import controller.interfaces.EmployeeController;
import data.DTO.User;
import data.DTO.UserUpdate;
import data.interfaces.EmployeeDAO;
import data.localStorageImpl.EmployeeDAOImpl;

import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by magnus
 */
public class EmployeeControllerImpl implements EmployeeController {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public List<User> getAllUsers() {
        return employeeDAO.getAll();
    }

    @Override
    public User getUserById(int id) {
        return employeeDAO.getById(id);
    }

    @Override
    public User addUser(User user) {
        return employeeDAO.add(user);
    }

    @Override
    public User updateUser(UserUpdate userUpdate, int userid) {
        User employee = getUserById(userid);
        employee.setFirstname(userUpdate.getFirstname());
        employee.setLastname(userUpdate.getLastname());
        employee.getCustomer().setAddress(userUpdate.getCustomer().getAddress());
        employee.getCustomer().setPhonenumber(userUpdate.getCustomer().getPhonenumber());
        employee.getCustomer().setMail(userUpdate.getCustomer().getMail());
        return employeeDAO.update(employee);
    }

    @Override
    public User removeUser(int id) {
        if (getUserById(id) != null)
            return employeeDAO.delete(id);

        throw new NotFoundException("Could not find a user with that id");
    }
}
