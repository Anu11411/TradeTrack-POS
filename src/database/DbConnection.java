/**
 * DbConnection is a singleton class responsible for managing the database connection.
 * It ensures that only one instance of the database connection exists throughout the application.
 * 
 * @author ianupriyarani
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    
    // The database connection object
    private Connection con;
    
    // The singleton instance of DbConnection
    private static DbConnection dbc;
    
    // Private constructor to prevent instantiation from other classes
    private DbConnection() {
        try {
            // Register the Oracle JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            // Establish the connection using the specified URL, schema name, and password
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "schema_name", "password");
        } catch (SQLException ex) {
            // Log any SQL exceptions that occur
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns the singleton instance of DbConnection.
     * If the instance does not exist, it creates one.
     *
     * @return The singleton instance of DbConnection.
     */
    public static DbConnection getDatabaseConnection() {
        if (dbc == null) {
            dbc = new DbConnection();
        }
        return dbc;
    }
    
    /**
     * Returns the database connection object.
     *
     * @return The database connection object.
     */
    public Connection getConnection() {
        return con;
    }
    
    /**
     * Main method for testing the DbConnection class.
     * Creates a new instance of DbConnection.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new DbConnection();
    }
}
