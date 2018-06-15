package controller.interfaces;

import data.DTO.*;

import java.util.List;

/**
 * Created by magnus
 */
public interface OrderController {
    Order addOrder(OrderRequest orderRequest);
    Order getOrder (int id);
    List<Order> getAllOrderByCustomerid (int userid);
    Order updateOrderStatus (int id, Status status);
    Order refundOrder (int id);
}
