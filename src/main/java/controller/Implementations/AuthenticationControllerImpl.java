package controller.Implementations;

import controller.ControllerRegistry;
import controller.interfaces.AuthenticationController;
import controller.interfaces.CustomerController;
import controller.interfaces.EmployeeController;
import data.DTO.LoginDetails;
import data.DTO.Role;
import data.DTO.User;
import util.JWTUtil;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by magnus
 */
public class AuthenticationControllerImpl implements AuthenticationController {
    //Using Json Web Token function to generate
    JWTUtil jwtUtil = new JWTUtil();
    CustomerController customerController = ControllerRegistry.getCustomerController();
    EmployeeController employeeController = ControllerRegistry.getEmployeeController();
    @Override
    public String login(LoginDetails loginDetails) {
        //String jwtToken = new JWTUtil().generateToken();
        User user = checkLoginDetails(loginDetails);
        if (user != null) {
            //Return the generatedToken
            return jwtUtil.generateToken(user);
        }
        else
            throw new NotAuthorizedException("Login failed, wrong username or password");
    }

    @Override
    public Role getRole(int userid) {
        //Both Employees and Customers
        List<User> users = new ArrayList<>();
        users.addAll(employeeController.getAllUsers());
        users.addAll(customerController.getAllUsers());

        for (User user : users) {
            if (user.getId() == userid) {
                return user.getRole();
            }
        }
        throw new NotFoundException("Could not solve your userid");
    }

    public User checkLoginDetails (LoginDetails loginDetails) {
        for (User user : customerController.getAllUsers()) {
            if (loginDetails.getUsername().equals(user.getUsername()))
                if (loginDetails.getPassword().equals(user.getPassword()))
                    return user;
        }

        for (User user : employeeController.getAllUsers()) {
            if (loginDetails.getUsername().equals(user.getUsername()))
                if (loginDetails.getPassword().equals(user.getPassword()))
                    return user;
        }

        return null;
    }
}
