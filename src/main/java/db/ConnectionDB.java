package db;

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
                Class.forName("org.sqlite.JDBC");
                ConnectionDB.connection = DriverManager.getConnection("jdbc:sqlite:martynique.db");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }

        return ConnectionDB.connection;
    }
}
