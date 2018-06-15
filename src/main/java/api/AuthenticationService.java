package api;

import controller.ControllerRegistry;
import controller.interfaces.AuthenticationController;
import data.DTO.LoginDetails;
import data.DTO.Role;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * Created by magnus
 */
@Path("authentication")
//Jackson processing JSON
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationService {
    //Injected by Jersey in AuthenticationFilter
    @Context
    ContainerRequestContext containerRequestContext;

    private AuthenticationController authenticationController = ControllerRegistry.getAuthenticationController();

    @Path("login")
    @POST
    public String login (LoginDetails loginDetails) {
        return authenticationController.login(loginDetails);
    }

    @Path("role")
    @GET
    @Secured({Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    public Role getRole () {
        //Getting saved user id to know who the user is
        if (containerRequestContext.getProperty("id") != null) {
            int userid = (int) containerRequestContext.getProperty("id");
            return authenticationController.getRole(userid);
        }
        else
            throw new BadRequestException("Could not solve your id");

    }
}
