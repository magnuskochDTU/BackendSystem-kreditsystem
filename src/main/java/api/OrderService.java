package api;

import controller.ControllerRegistry;
import controller.interfaces.OrderController;
import data.DTO.Order;
import data.DTO.OrderRequest;
import data.DTO.Role;
import data.DTO.Status;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by magnus
 */
@Path("order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderService {
    @Context
    private ContainerRequestContext containerRequestContext;

    private OrderController orderController = ControllerRegistry.getOrderController();

    @POST
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Order addOrder (OrderRequest orderRequest) {
        return orderController.addOrder(orderRequest);
    }

    @GET
    @Path("{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Order getOrder (@PathParam("id") int id) {
        return orderController.getOrder(id);
    }

    @GET
    @Path("customer/{customerid}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public List<Order> getAllOrderByCustomerid (@PathParam("customerid") int customerid) {
        return orderController.getAllOrderByCustomerid(customerid);
    }

    @GET
    @Path("customer/self")
    @Secured(Role.CUSTOMER)
    public List<Order> getAllOrderForUser () {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return orderController.getAllOrderByCustomerid(userid);
        }
        throw new BadRequestException("Could not solve your id");
    }

    @PUT
    @Path("{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Order updateOrderStatus (@PathParam("id") int id, Status status) {
        return orderController.updateOrderStatus(id, status);
    }

    @POST
    @Path("refund/{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Order refundOrder (@PathParam("id") int id) {
        return orderController.refundOrder(id);
    }

}
