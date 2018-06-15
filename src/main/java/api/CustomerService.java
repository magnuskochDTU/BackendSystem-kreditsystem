package api;

import controller.ControllerRegistry;
import controller.interfaces.AccountController;
import controller.interfaces.CustomerController;
import data.DTO.Account;
import data.DTO.Role;
import data.DTO.User;
import data.DTO.UserUpdate;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by magnus
 */
@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerService {
    private CustomerController customerController = ControllerRegistry.getCustomerController();
    private AccountController accountController = ControllerRegistry.getAccountController();

    //To get details about the user making the request
    @Context
    ContainerRequestContext containerRequestContext;

    @GET
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public List<User> getCustomers () {
        return customerController.getAllUsers();
    }

    @GET
    //Finding the user information from the token in authFilter
    @Path("self")
    @Secured(Role.CUSTOMER)
    public User getCustomerSelf () {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return customerController.getUserById(userid);
        }
        throw new BadRequestException("Could not solve your id");
    }

    @GET
    @Path("{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public User getCustomer (@PathParam("id") int id) {
        return customerController.getUserById(id);
    }

    @POST
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public User addCustomer (User customer) {
        return customerController.addUser(customer);
    }

    @PUT
    //Finding the user information from the token in authFilter
    @Path("self")
    @Secured(Role.CUSTOMER)
    public User updateCustomerForUser (UserUpdate userUpdate) {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return customerController.updateUser(userUpdate, userid);
        }
        throw new BadRequestException("Could not solve your id");
    }

    @DELETE
    @Path("{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public User removeCustomer (@PathParam("id") int id) {
        return customerController.removeUser(id);
    }

    @DELETE
    //Finding the user information from the token in authFilter
    @Path("self")
    @Secured(Role.CUSTOMER)
    public User removeCustomerForUser () {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return customerController.removeUser(userid);
        }
        throw new BadRequestException("Could not solve your id");
    }

    @GET
    @Path("account/self")
    //Finding the user information from the token in authFilter
    @Secured(Role.CUSTOMER)
    public Account getAccountForUser () {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return accountController.getAcountByUserId(userid);
        }
        throw new BadRequestException("Could not solve your id");
    }

    @GET
    @Path("account/{userid}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Account getAccount (@PathParam("userid") int userid) {
        return accountController.getAcountByUserId(userid);
    }

    @Path("account/{userid}/withdraw/{amount}")
    @POST
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Account withdraw (@PathParam("userid") int userid, @PathParam("amount") int amount) {
        return accountController.withdraw(userid, amount);
    }

    //Finding the user information from the token in authFilter
    @Path("account/self/withdraw/{amount}")
    @POST
    @Secured(Role.CUSTOMER)
    public Account withdrawForUser (@PathParam("amount") int amount) {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return accountController.withdraw(userid, amount);
        }
        throw new BadRequestException("Could not solve your id");
    }


    @Path("account/{userid}/deposit/{amount}")
    @POST
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Account deposit (@PathParam("userid") int userid, @PathParam("amount") int amount) {
        return accountController.deposit(userid, amount);
    }

    //Finding the user information from the token in authFilter
    @Path("account/self/deposit/{amount}")
    @POST
    @Secured(Role.CUSTOMER)
    public Account depositForUser (@PathParam("amount") int amount) {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return accountController.deposit(userid, amount);
        }
        throw new BadRequestException("Could not solve your id");
    }
}
