package data.DTO;

/**
 * Created by magnus
 */
public class ProductPost {
    private int id;
    private String name;
    private double price;
    private int categoryid;

    private static int counter = 1;

    public ProductPost() {
        id = counter;
        counter++;
    }

    public ProductPost(String name, double price, int categoryid) {
        this.id = counter;
        counter++;
        this.name = name;
        this.price = price;
        this.categoryid = categoryid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getNewId () {
        counter++;
        return counter-1;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }
}
