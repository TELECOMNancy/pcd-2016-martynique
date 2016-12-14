package db;

import models.Identifiable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ModelDB {

    /*protected Connection connection;
    protected String table;

    public ModelDB(String table) {
        this.connection = ConnectionDB.getInstance();
        this.table = table;

    }*/

   /*public void delete(Identifiable i) {
        try {
            Statement st  = this.connection.createStatement();
            PreparedStatement prep = this.connection.prepareStatement(deleteQuery());
            prep.setInt(1, i.getID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static String insertQuery(String table) {  return "INSERT INTO " + table; }

    public  static String findByIdQuery(String table) {  return "SELECT * FROM " + table + " WHERE id = ?"; }
    
    

    public static String updateQuery(String table) {
        return "UPDATE  " + table;
    }

    public  static String deleteQuery(String table) {
        return "DELETE FROM " + table  + " WHERE id = (?)";
    }

    public static String allQuery(String table) {
        return "SELECT * FROM " + table;
    }

}
