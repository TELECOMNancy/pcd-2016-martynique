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

    public String insertQuery() {
        return "INSERT INTO " + this.table;
    }

    public String updateQuery() {
        return "UPDATE  " + this.table;
    }

    public String deleteQuery() {
        return "DELETE FROM " + this.table  + " WHERE id = (?)";
    }

    public String allQuery() {
        return "SELECT * FROM " + this.table;
    }

    public abstract void createTable();

}
