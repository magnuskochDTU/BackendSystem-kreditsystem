package data.interfaces;

import data.DTO.Category;
import data.DTO.Product;

import java.util.List;

/**
 * Created by magnus
 */
public interface ProductDAO {
    List<Product> getAllProducts ();
    Product getProductById (int id);
    Product addProduct (Product product, int categoryid);
    Product updateProduct (Product product);
    Product removeProduct (int id);

    Category getCategoryById (int id);
    Category addCategory (Category category);
    Category updateCategory (Category category);
    Category removeCategory (int id);
    List<Category> getAllCategories ();
}
