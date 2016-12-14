package db;

import models.Identifiable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ModelDB<T> implements CRUD<T> {

    protected Connection connection;
    protected String table;

    public ModelDB(String table) {
        this.connection = ConnectionDB.getInstance();
        this.table = table;

    }

    public void delete(Identifiable i) {
        try {
            Statement st  = this.connection.createStatement();
            PreparedStatement prep = this.connection.prepareStatement(this.deleteQuery());
            prep.setInt(1, i.getID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String insertQuery() {  return "INSERT INTO " + table; }

    public String findByIdQuery() {  return "SELECT * FROM " + table + " WHERE id = ?"; }

    public String updateQuery() {
        return "UPDATE  " + table;
    }

    public String deleteQuery() {
        return "DELETE FROM " + table  + " WHERE id = (?)";
    }

    public String allQuery() {
        return "SELECT * FROM " + table;
    }

}
