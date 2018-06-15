package controller.Implementations;

import controller.interfaces.ProductController;
import data.DTO.Category;
import data.DTO.Product;
import data.interfaces.ProductDAO;
import data.localStorageImpl.ProductDAOImpl;

import java.util.List;

/**
 * Created by magnus
 */
public class ProductControllerImpl implements ProductController{
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public Product addProduct(Product product, int categoryid) {
        return productDAO.addProduct(product, categoryid);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public Product removeProduct(int id) {
        return productDAO.removeProduct(id);
    }

    @Override
    public Category getCategoryById(int categoryid) {
        return productDAO.getCategoryById(categoryid);
    }

    @Override
    public Category addCategory(Category category) {
        return productDAO.addCategory(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return productDAO.updateCategory(category);
    }

    @Override
    public Category removeCategory(int categoryid) {
        return productDAO.removeCategory(categoryid);
    }

    @Override
    public List<Category> getAllCategories() {
        return productDAO.getAllCategories();
    }
}
