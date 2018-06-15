package api;

import controller.ControllerRegistry;
import controller.interfaces.EmployeeController;
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
@Path("Employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeService {
    private EmployeeController employeeController = ControllerRegistry.getEmployeeController();

    //To get details about the user making the request
    @Context
    ContainerRequestContext containerRequestContext;

    @GET
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public List<User> getEmployees () {
        return employeeController.getAllUsers();
    }

    @GET
    @Path("{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public User getEmployee (@PathParam("id") int id) {
        return employeeController.getUserById(id);
    }

    @GET
    @Path("self")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public User getEmployeeForUser () {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return employeeController.getUserById(userid);
        }
        throw new BadRequestException("Could not solve your id");
    }

    @POST
    @Secured({Role.ADMIN})
    public User addEmployee (User employee) {
        return employeeController.addUser(employee);
    }

    @PUT
    @Path("self")
    @Secured({Role.EMPLOYEE})
    public User updateEmployeeForUser (UserUpdate employee) {
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return employeeController.updateUser(employee, userid);
        }
        throw new BadRequestException("Could not solve your id");
    }

    @DELETE
    @Path("{id}")
    @Secured(Role.ADMIN)
    public User removeEmployee (@PathParam("id") int id) {
        return employeeController.removeUser(id);
    }
}
