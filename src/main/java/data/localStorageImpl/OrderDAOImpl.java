package data.localStorageImpl;

import data.DTO.*;
import data.interfaces.OrderDAO;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by magnus
 */
public class OrderDAOImpl implements OrderDAO {

    //For testing and for default having some orders to look at :)
    static List<Order> orders = new ArrayList<>();
    static Order order1 = new Order(Status.success, CustomerDAOImpl.customers.get(1), EmployeeDAOImpl.employees.get(1),ProductDAOImpl.products1);
    static Order order2 = new Order(Status.success, CustomerDAOImpl.customers.get(1), EmployeeDAOImpl.employees.get(1), ProductDAOImpl.products2);

    static {
        orders.add(order1);
        orders.add(order2);
    }

    @Override
    public Order addOrder(User customer, User employee, List<Product> products) {
        Order order = new Order(Status.success, customer, employee, products);
        int newId = Order.getNewInt();
        order.setId(newId);
        orders.add(order);
        return getOrder(newId);
    }

    @Override
    public Order getOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }
        throw new NotFoundException("Could not find order");
    }

    @Override
    public List<Order> getAllOrderByCustomerid(int userid) {
        List<Order> ordersToReturn = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().getId() == userid)
                ordersToReturn.add(order);
        }
        if (ordersToReturn.isEmpty())
            throw new NotFoundException("Could not find any order under this customer");
        else
            return ordersToReturn;
    }
    @Override
    public Order updateOrderStatus(int id, Status status) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setStatus(status);
                return order;
            }
        }
        throw new NotFoundException("Could not find order");
    }

    @Override
    public Order refundOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                double countTotal = 0;
                for (Product product : order.getProducts()) {
                    countTotal += product.getPrice();
                }

                order.getCustomer().getCustomer().getAccount().deposit(countTotal);
                order.setStatus(Status.refunded);
                return order;
            }
        }
        throw new NotFoundException("Could not find order");
    }
}
