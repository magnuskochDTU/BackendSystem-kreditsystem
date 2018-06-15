package data.localStorageImpl;

import data.DTO.Category;
import data.DTO.Product;
import data.interfaces.ProductDAO;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by magnus
 */
public class ProductDAOImpl implements ProductDAO {

    //Creating default products and categories
    static List<Category> categories = new ArrayList<>();
    static Product product1 = new Product("Herreklip", 390.00);
    static Product product2 = new Product("Skæg", 125.00);
    static Product product3 = new Product("Dameklip (uden føn)", 430.00);
    static Product product4 = new Product("Dameklip (inkl. føn)", 600.00);
    static Product product5 = new Product("0-5 år", 300.00);
    static Product product6 = new Product("Shampoo", 200.00);
    static List<Product> products1 = new ArrayList<>();
    static List<Product> products2 = new ArrayList<>();
    static List<Product> products3 = new ArrayList<>();
    static List<Product> products4 = new ArrayList<>();
    static List<Product> products = new ArrayList<>();
    static Category category1 = new Category("Herreklip");
    static Category category2 = new Category("Dameklip");
    static Category category3 = new Category("Børneklip");
    static Category category4 = new Category("Produkter");


    static {
        products1.add(product1);
        products1.add(product2);
        products2.add(product3);
        products2.add(product4);
        products3.add(product5);
        products4.add(product6);
        products.addAll(products1);
        products.addAll(products2);
        products.addAll(products3);
        products.addAll(products4);
        category1.setProducts(products1);
        category2.setProducts(products2);
        category3.setProducts(products3);
        category4.setProducts(products4);
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        throw new NotFoundException("Could not find product");
    }

    @Override
    public Product addProduct(Product product, int categoryid) {
        int newId = Product.getNewId();
        product.setId(newId);
        products.add(product);
        for (Category category : categories) {
            if (category.getId() == categoryid) {
                category.addProduct(product);
                return getProductById(newId);
            }
        }
        throw new NotFoundException("Could not find product");
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == updatedProduct.getId()) {
                product = updatedProduct;
                return product;
            }
        }
        throw new NotFoundException("Could not find product");
    }

    @Override
    public Product removeProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return getProductById(id);
            }
        }
        throw new NotFoundException("Could not find product");
    }

    @Override
    public Category getCategoryById(int id) {
        for (Category category : categories) {
            if (category.getId() == id)
                return category;
        }
        throw new NotFoundException("Could not find product");
    }

    @Override
    public Category addCategory(Category category) {
        int newId = Category.getNewId();
        category.setId(newId);
        categories.add(category);
        return getCategoryById(newId);
    }

    @Override
    public Category updateCategory(Category updatedCategory) {
        for (Category category : categories) {
            if (category.getId() == updatedCategory.getId()) {
                category = updatedCategory;
                return category;
            }
        }
        throw new NotFoundException("Could not find product");
    }

    @Override
    public Category removeCategory(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                categories.remove(category);
                return getCategoryById(id);
            }
        }
        throw new NotFoundException("Could not find product");
    }

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
}
