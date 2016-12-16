package db;

public abstract class ModelDB {

    public static String insertQuery(String table) {  return "INSERT INTO " + table; }

    public  static String findByIdQuery(String table) {  return "SELECT * FROM " + table + " WHERE id = ?"; }

    public  static String whereQuery(String table) {  return "SELECT * FROM " + table + " WHERE "; }

    public static String updateQuery(String table) {
        return "UPDATE " + table + " SET ";
    }

    public  static String deleteQuery(String table) {
        return "DELETE FROM " + table  + " WHERE id = (?)";
    }

    public static String allQuery(String table) {
        return "SELECT * FROM " + table;
    }
    


}
