package data.localStorageImpl;

import data.DTO.Customer;
import data.DTO.User;
import data.interfaces.CustomerDAO;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by magnus
 */
public class CustomerDAOImpl implements CustomerDAO {
    //Default generated customers
    static List<User> customers = new ArrayList<>();
    static User customer1 = new User("Caro", "123", "Caroline","Ankjær", new Customer("24636526", "caro@gmail.com", "Kollegiebakken 13"));
    static User customer2 = new User("Pau", "123", "Paul", "Felter", new Customer("43244535", "Paul@gmail.com", "Carlsberg 11"));

    //For generating static default data
    static {
        customers.add(customer1);
        customers.add(customer2);
    }

    @Override
    public User add(User customer) {
        //Function to get new autoIncrement id
        int newId = User.getNewId();
        customer.setId(newId);

        customers.add(customer);
        return getById(newId);
    }

    @Override
    public User update(User updatedCustomer) {
        for (User customer : customers) {
            if (customer.getId() == updatedCustomer.getId()) {
                customer = updatedCustomer;
                return customer;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public List<User> getAll() {
        return customers;
    }

    @Override
    public User getById(int id) {
        for (User customer : customers) {
            if (customer.getId() == id)
                return customer;
        }
        throw new NotFoundException("Could not find customer");
    }

    @Override
    public User delete(int id) {
        if (getById(id) != null) {
            for (User customer : customers) {
                if (customer.getId() == id)
                    customers.remove(customer);
            }
            return getById(id);
        }
        throw new NotFoundException("Could not find customer");
    }
}
