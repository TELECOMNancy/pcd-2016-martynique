package tests;

import java.sql.Connection;
import java.sql.DriverManager;

import app.Configuration;
import db.ConnectionDB;

public class ConnectionDBTest {

    private static Connection connection;
    
    public static Connection getInstance() {
        if(ConnectionDBTest.connection == null) {
            try {
                System.out.println("-- " + Configuration.getInstance().getDbPath());
                Class.forName("org.sqlite.JDBC");
                ConnectionDBTest.connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }

        return ConnectionDBTest.connection;
    }
}
