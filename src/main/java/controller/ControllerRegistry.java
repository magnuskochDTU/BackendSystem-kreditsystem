package controller;

import controller.Implementations.*;
import controller.interfaces.*;

/**
 * Created by magnus
 */

//To get the same instance of all controllers
public class ControllerRegistry {
    private static AccountController accountController;
    private static AuthenticationController authenticationController;
    private static CustomerController customerController;
    private static EmployeeController employeeController;
    private static OrderController orderController;
    private static ProductController productController;

    public static AccountController getAccountController () {
        if (accountController == null) accountController = new AccountControllerImpl();
        return accountController;
    }

    public static AuthenticationController getAuthenticationController () {
        if (authenticationController == null) authenticationController = new AuthenticationControllerImpl();
        return authenticationController;
    }

    public static CustomerController getCustomerController () {
        if (customerController == null) customerController = new CustomerControllerImpl();
        return customerController;
    }

    public static EmployeeController getEmployeeController () {
        if (employeeController == null) employeeController = new EmployeeControllerImpl();
        return employeeController;
    }

    public static OrderController getOrderController () {
        if (orderController == null) orderController = new OrderControllerImpl();
        return orderController;
    }

    public static ProductController getProductController () {
        if (productController == null) productController = new ProductControllerImpl();
        return productController;
     }
}
