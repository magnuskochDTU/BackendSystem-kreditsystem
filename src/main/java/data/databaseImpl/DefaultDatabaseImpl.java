package data.databaseImpl;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Lavet af Magnus Stjernborg Koch
 */

public class DefaultDatabaseImpl <T> {
    //move to config
    private static final String databaseUrl = "jdbc:mysql://kreditsystem-database.czir2ycmcwhq.us-east-2.rds.amazonaws.com";
    private static final String databaseUsername = "kreditsystem";
    private static final String databasePassword = "secret";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.driver");
            return DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        } catch (ClassNotFoundException e) {
            throw new InternalServerErrorException("Mysql driver class not found: "+e);
        } catch (SQLException e) {
            throw new ServiceUnavailableException("Database connection error: "+e);
        }

    }
}
