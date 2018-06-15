package filter;

import api.Secured;
import controller.ControllerRegistry;
import controller.interfaces.CustomerController;
import controller.interfaces.EmployeeController;
import data.DTO.Role;
import data.DTO.User;
import io.jsonwebtoken.Claims;
import util.JWTUtil;

import javax.annotation.Priority;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by magnus
 */
@Priority(Priorities.AUTHENTICATION)
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    //The header sended by the client which include the json web token
    @Context
    HttpHeaders httpHeaders;
    //To get the annotations for the requested resource
    @Context
    ResourceInfo resourceInfo;

    private CustomerController customerController = ControllerRegistry.getCustomerController();
    private EmployeeController employeeController = ControllerRegistry.getEmployeeController();

    //Functions for generating and validating the json web token
    private JWTUtil jwtUtil = new JWTUtil();

    @Override
    public void filter(@Context ContainerRequestContext containerRequestContext) throws IOException {

        //securd is now set to method level
        Secured secured = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
        //Checker for method level proctection
        if (secured == null)
            //secured is now set to class level
            secured = resourceInfo.getResourceClass().getAnnotation(Secured.class);

        //Checks for class protections
        if (secured == null)
            //No protection get out of the filter
            return;

        //Getting the json web token from httpheader
        String authenticationHeader = httpHeaders.getHeaderString(httpHeaders.AUTHORIZATION);
        if (authenticationHeader == null)
            throw new NotAuthorizedException("You need to login before using this service");
        else
            //Validate json web token
            if (validate(authenticationHeader, containerRequestContext)) {
                //Check if role is the same as the required role
                if (checkRole(secured, containerRequestContext))
                    return;
                else
                    throw new ForbiddenException("Your role is not authorized");
            }
            else
                throw new NotAuthorizedException("");
    }

    private boolean validate (String authenticationHeader, ContainerRequestContext containerRequestContext) {
        //Splitting the json web token to remove the Bearer
        String[] jwt = authenticationHeader.split(" ");
        if (jwt[1] != null) {
            //validating json web token and getting claims
            Claims claims = jwtUtil.parseToken(jwt[1]);
            //returning saved id from json web token
            int id = (int) claims.get("id");

            if (id > 0) {
                //Setting id into the containerRequestContext to using later in the services
                containerRequestContext.setProperty("id", id);
                return true;
            }
        }
        return false;
    }

    private boolean checkRole (Secured secured, ContainerRequestContext containerRequestContext) {
        //Get both employees and customers
        List<User> users = new ArrayList<>();
        users.addAll(employeeController.getAllUsers());
        users.addAll(customerController.getAllUsers());

        for (User user : users) {
            if (user.getId() == (Integer) containerRequestContext.getProperty("id")) {
                for (Role role : secured.value()) {
                    if (role.equals(user.getRole()))
                        return true;
                }
                return false;
            }
        }
        return false;
    }
}
