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

            prep.setString(1, v.getTitle());
            prep.setString(2, v.getThumbnail());
            prep.setString(3, v.getCode());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            v.setID(tableKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* @Override
    public List<Video> all() {
        List<Video> list = new ArrayList<Video>();
        try {
            Statement st  = this.connection.createStatement();
            ResultSet rs = st.executeQuery(allQuery());

            while(rs.next()){
                //Favorite tmp = new Favorite(rs.getInt("id_video"))
                Video tmp = new Video(rs.getString("title"), rs.getString("thumbnail"), rs.getString("code"));
                tmp.setID(rs.getInt("id_video"));
                list.add(tmp);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }*/

    
    public void update(Video v) {
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(this.updateQuery());
            prep.setString(1, v.getTitle());
            prep.setString(2, v.getThumbnail());
            prep.setString(3, v.getCode());
            prep.setInt(4, v.getID());
            prep.executeUpdate();
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

            if (rs.next()) {
                v = new Video(rs.getString("title"), rs.getString("thumbnail"), rs.getString("code"));
                v.setID(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }


    public String insertQuery() {
        return ModelDB.insertQuery(TABLE) + "(title, thumbnail, code) VALUES(?,?,?)";
    }

    
    public String updateQuery() {
        return ModelDB.updateQuery(TABLE) + "title = (?), thumbnail  = (?), code = (?) WHERE id_video = (?)";
    }

    
    public String deleteQuery() {
        return ModelDB.deleteQuery(TABLE) + " WHERE id_video = (?)";
    }

    public static void createTable() {
        String createString = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title varchar(255) NOT NULL, " +
                //"url varchar(255) NOT NULL, " +
                "thumbnail varchar(255), " +
                "code varchar(255) NOT NULL)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
