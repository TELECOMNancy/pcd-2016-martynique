package db;

import java.sql.Connection;

public abstract class ModelDB<T> implements CRUD<T> {

    protected Connection connection;
    protected String table;

    public ModelDB(String table) {
        this.connection = ConnectionDB.getInstance();
        this.table = table;
    }

    public String insertQuery() {
        return "INSERT INTO " + this.table;
    }

    public String updateQuery() {
        return "UPDATE  " + this.table;
    }

    public String deleteQuery() {
        return "DELETE FROM " + this.table;
    }

    public String allQuery() {
        return "SELECT * FROM " + this.table;
    }

}
