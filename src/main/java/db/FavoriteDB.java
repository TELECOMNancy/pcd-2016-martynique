package db;

import models.Favorite;
import models.Video;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FavoriteDB{

;
    private static final String TABLE = "Favorites";





    public static void create(Favorite f) {
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(insertQuery(), Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, f.getFavorite().getID());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            f.setID(tableKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void update(Favorite f) {
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(updateQuery());
            prep.setInt(1, f.getFavorite().getID());
            prep.setInt(2, f.getID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Favorite findById(int id) {
        Favorite f = null;
        try {
            PreparedStatement prep = ConnectionDB.getInstance().prepareStatement(ModelDB.findByIdQuery(TABLE));
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();

            if(rs.next()) {
                f = new Favorite(new Video(rs.getString("title"), rs.getString("thumbnail"), rs.getString("code")));
                f.setID(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

 
    public List<Favorite> all() {
        List<Favorite> list = new ArrayList<Favorite>();
        try {
            Statement st  = ConnectionDB.getInstance().createStatement();
            ResultSet rs = st.executeQuery(allQuery());

            while(rs.next()){

                Favorite tmp = new Favorite(VideoDB.findById(rs.getInt("id_video")));
                tmp.setID(rs.getInt("id"));
                list.add(tmp);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

 
    public static String allQuery() {
        return ModelDB.allQuery(TABLE) + " NATURAL JOIN Videos";
    }

  
    public static String insertQuery() {
        return ModelDB.insertQuery(TABLE) + "(id_video) VALUES(?)";

    }


    public static String updateQuery() {
        return ModelDB.updateQuery(TABLE) + " SET id_video  = (?) WHERE id_favorite = (?)";
    }


    public static String deleteQuery() {
        return ModelDB.deleteQuery(TABLE) + " WHERE id_favorite = (?)";
    }

    public static void createTable() {
        String createString = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "id INTEGER PRIMARY KEY, " +
                "id_video INTEGER NOT NULL, " +
                "FOREIGN KEY (id_video) REFERENCES Videos(id))";

        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
