package data.databaseImpl;

/**
 * Lavet af Magnus Stjernborg Koch
 */
/*
public class CustomerDatabaseImpl extends DefaultDatabaseImpl<Customer> implements CustomerDAO {
    private CallableStatement callableStatement = null;

    @Override
    public void add(Customer customer) {
        try {
            callableStatement = getConnection().prepareCall("CALL CustomerInsert(?,?)");
            callableStatement.setString(1, "test");
            callableStatement.setString(2, "test");
            callableStatement.execute();
        }
        catch (SQLException e) {
            throw new ServiceUnavailableException("SQL error: "+e);
        }
        finally {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println("Failed to close callableStatement in ");
            }
        }
    }

    @Override
    public void addsMultiple(List<Customer> elements) {

    }

    @Override
    public Customer update(int id, Customer element) {
        return null;
    }

    @Override
    public List<Customer> getAll() {

        return null;
    }

    @Override
    public Customer getById(int id) {
        try {
            callableStatement = getConnection().prepareCall("CALL CustomerGetById(?)");
            callableStatement.setInt(1, id);
            ResultSet result =  callableStatement.executeQuery();
            while(result.next()){
                Customer customer = new Customer(result.getString(1), result.getString(2),result.getString(3));
            }

        }
        catch (SQLException e) {
            throw new ServiceUnavailableException("SQL error: "+e);
        }
        finally {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println("Failed to close callableStatement in ");
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }
}*/
