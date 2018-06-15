package data.interfaces;

import data.DTO.*;

import java.util.List;

/**
 * Created by magnus
 */
public interface OrderDAO {
    Order addOrder(User customer, User employee, List<Product> products);
    Order getOrder (int id);
    List<Order> getAllOrderByCustomerid (int userid);
    Order updateOrderStatus (int id, Status status);
    Order refundOrder (int id);
}
