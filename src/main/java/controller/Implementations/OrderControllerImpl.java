package controller.Implementations;

import controller.interfaces.AccountController;
import controller.interfaces.CustomerController;
import controller.interfaces.EmployeeController;
import controller.interfaces.OrderController;
import data.DTO.*;
import data.interfaces.OrderDAO;
import data.localStorageImpl.OrderDAOImpl;

import javax.ws.rs.BadRequestException;
import java.util.List;

/**
 * Created by magnus
 */
public class OrderControllerImpl implements OrderController {
    private OrderDAO orderDAO = new OrderDAOImpl();
    private CustomerController customerController = new CustomerControllerImpl();
    private EmployeeController employeeController = new EmployeeControllerImpl();
    private AccountController accountController = new AccountControllerImpl();

    //Adding order is both making the money transfer and creating the order for later lookup
    @Override
    public Order addOrder(OrderRequest orderRequest) {
        Account account = accountController.getAcountByUserId(orderRequest.getCustomerid());
        if (account != null) {
            int totalPrice = 0;
            for (Product product : orderRequest.getProducts()) {
                totalPrice += product.getPrice();
            }
            account.withdraw(totalPrice);
            return orderDAO.addOrder(
                    customerController.getUserById(orderRequest.getCustomerid()),
                    employeeController.getUserById(orderRequest.getEmployeeid()),
                    orderRequest.getProducts());
        }
        else
            throw new BadRequestException("User does not have an account");
    }

    @Override
    public Order getOrder(int id) {
        return orderDAO.getOrder(id);
    }

    @Override
    public List<Order> getAllOrderByCustomerid(int userid) {
        return orderDAO.getAllOrderByCustomerid(userid);
    }

    //Chance status, example if an order is refunded or removed
    @Override
    public Order updateOrderStatus(int id, Status status) {
        return orderDAO.updateOrderStatus(id, status);
    }

    @Override
    public Order refundOrder(int id) {
        return orderDAO.refundOrder(id);
    }
}
