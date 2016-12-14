package db;

import models.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VideoDB {

    
    private static final String TABLE = "Videos";



   
    public static void create(Video v) {
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(ModelDB.insertQuery(TABLE), Statement.RETURN_GENERATED_KEYS);

            prep.setString(1, v.getCode());
            prep.setString(2, v.getTitle());
            prep.setString(3, v.getThumbnail());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            v.setID(tableKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Video findById(int id) {
        Video v = null;
        try {
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(ModelDB.findByIdQuery(TABLE));
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();

            if (rs.next())
                v = new Video(rs.getString("title"), rs.getString("thumbnail"), rs.getString("code"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }


    public String insertQuery() {
        return ModelDB.insertQuery(TABLE) + "(id, title, thumbnail) VALUES(?,?,?)";
    }

    
    public String updateQuery() {
        return ModelDB.updateQuery(TABLE) + "title = (?), thumbnail  = (?) WHERE id = (?)";
    }

    
    public String deleteQuery() {
        return ModelDB.deleteQuery(TABLE) + " WHERE id = (?)";
    }

    public static void createTable() {
        String createString = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "id varchar(255) PRIMARY KEY, " +
                "title varchar(255) NOT NULL, " +
                //"url varchar(255) NOT NULL, " +
                "thumbnail varchar(255))";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
