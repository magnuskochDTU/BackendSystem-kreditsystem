package data.DTO;

/**
 * Created by magnus
 */
public class UserUpdate {
    private String firstname;
    private String lastname;
    private CustomerUpdate customer;


    public UserUpdate() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public CustomerUpdate getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerUpdate customer) {
        this.customer = customer;
    }
}
