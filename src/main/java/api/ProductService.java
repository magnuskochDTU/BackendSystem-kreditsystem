package api;

import controller.ControllerRegistry;
import controller.interfaces.ProductController;
import data.DTO.Category;
import data.DTO.Product;
import data.DTO.Role;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by magnus
 */
@Path("product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductService {
    //To get details about the user making the request
    @Context
    ContainerRequestContext containerRequestContext;

    private ProductController productController = ControllerRegistry.getProductController();

    @GET
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public List<Product> getAllProducts () {
        return productController.getAllProducts();
    }

    @GET
    @Path("{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Product getProduct (@PathParam("id") int id) {
        return productController.getProductById(id);
    }

    @POST
    @Path("{categoryid}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Product addProduct (Product product, @PathParam("categoryid") int categoryid) {
        return productController.addProduct(product, categoryid);
    }

    @PUT
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Product updateProduct (Product product) {
        return productController.updateProduct(product);
    }

    @DELETE
    @Path("{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Product removeProduct (@PathParam("id") int id) {
        return productController.removeProduct(id);
    }

    @GET
    @Path("category/{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Category getCategory (@PathParam("id") int id) {
        return productController.getCategoryById(id);
    }

    @POST
    @Path("category")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Category addCategory (Category category) {
        return productController.addCategory(category);
    }

    @PUT
    @Path("category")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Category updateCategory (Category category) {
        return productController.updateCategory(category);
    }

    @DELETE
    @Path("category/{id}")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public Category removeCategory (@PathParam("id") int id) {
        return productController.removeCategory(id);
    }

    @GET
    @Path("category")
    @Secured({Role.ADMIN, Role.EMPLOYEE})
    public List<Category> getAllCategories () {
        return productController.getAllCategories();
    }
}

