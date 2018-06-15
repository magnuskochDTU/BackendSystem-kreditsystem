package data.localStorageImpl;

import data.DTO.Role;
import data.DTO.User;
import data.interfaces.EmployeeDAO;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by magnus
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    //Default employees
    static List<User> employees = new ArrayList<>();
    static User employee1 = new User("Mat", "1234", "Mathias","Hansen");
    static User employee2 = new User("Futte", "123", "Nicklas", "Thyssen");
    static User employee3 = new User("Magnus", "123", "Magnus","Koch", Role.ADMIN);

    //For adding the static employees
    static {
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
    }


    @Override
    public User add(User employee) {
        int newId = User.getNewId();
        employee.setId(newId);

        employees.add(employee);
        return employee;
    }

    @Override
    public User update(User updatedEmployee) {
        for (User employee : employees) {
            if (employee.getId() == updatedEmployee.getId()) {
                employee = updatedEmployee;
                return employee;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public List<User> getAll() {
        return employees;
    }

    @Override
    public User getById(int id) {
        for (User employee : employees) {
            if (employee.getId() == id)
                return employee;
        }
        throw new NotFoundException("Could not find employee");
    }

    @Override
    public User delete(int id) {
        if (getById(id) != null) {
            for (User employee : employees) {
                if (employee.getId() == id) {
                    employees.remove(employee);
                    return employee;
                }
            }
        }
        throw new NotFoundException("Could not find employee");
    }
}
