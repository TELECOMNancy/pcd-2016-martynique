package db;

import app.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Singleton to have only one instance of Connection for JDBC
 */
public class ConnectionDB {

    private static Connection connection;
    
    public static Connection getInstance() {
        if(ConnectionDB.connection == null) {
            try {
                System.out.println("-- " + Configuration.getInstance().getDbPath());
                Class.forName("org.sqlite.JDBC");
                ConnectionDB.connection = DriverManager.getConnection("jdbc:sqlite:" + Configuration.getInstance().getDbPath());
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }

        return ConnectionDB.connection;
    }
}
