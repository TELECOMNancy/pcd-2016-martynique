package db;

import models.Favorite;
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
            prep = ConnectionDB.getInstance().prepareStatement(VideoDB.insertQuery(), Statement.RETURN_GENERATED_KEYS);

            prep.setString(1, v.getCode());
            prep.setString(2, v.getTitle());
            prep.setString(3, v.getThumbnail());
            prep.setBoolean(4, v.isFavorite());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            v.setID(tableKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Video findById(String id) {
        Video v = null;
        try {
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(ModelDB.findByIdQuery(TABLE));
            prep.setString(1, id);
            ResultSet rs = prep.executeQuery();

            if (rs.next())
                v = new Video(rs.getString("title"), rs.getString("thumbnail"), rs.getString("code"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }

    public static void setFavorite(Video v) {
        if(VideoDB.findById(v.getCode()) == null)
            VideoDB.create(v);
        else
            update(v);
    }

    public static void update(Video v) {
        try {
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(VideoDB.updateQuery());
            prep.setString(1, v.getTitle());
            prep.setString(2, v.getThumbnail());
            prep.setBoolean(3, v.isFavorite());
            prep.setString(4, v.getCode());
            prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String insertQuery() {
        return ModelDB.insertQuery(TABLE) + "(id, title, thumbnail, favorite) VALUES(?,?,?,?)";
    }
    
    public static String updateQuery() {
        return ModelDB.updateQuery(TABLE) + "title = ?, thumbnail  = ?, favorite = ? WHERE id = ?";
    }

    public String deleteQuery() {
        return ModelDB.deleteQuery(TABLE) + " WHERE id = (?)";
    }

    private static String favoritesQuery() {
        return ModelDB.whereQuery(TABLE) + "favorite = 1";
    }

    public static void createTable() {
        String createString = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "id varchar(255) PRIMARY KEY, " +
                "title varchar(255) NOT NULL, " +
                "favorite INTEGER NOT NULL DEFAULT 0 CHECK(favorite == 1 OR favorite == 0), " +
                "thumbnail varchar(255))";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Favorite> getFavorites() {
        List<Favorite> list = new ArrayList<Favorite>();
        try {
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(VideoDB.favoritesQuery());
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                list.add(new Favorite(new Video(rs.getString("title"), rs.getString("thumbnail"), rs.getString("id"))));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
