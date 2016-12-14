package db;

import models.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VideoDB extends ModelDB<Video> {

    private Video v;
    private static final String TABLE = "Videos";

    public VideoDB(Video v) {
        super(TABLE);
        this.v = v;
    }

    @Override
    public void create() {
        try {
            Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = this.connection.prepareStatement(this.insertQuery(), Statement.RETURN_GENERATED_KEYS);

            prep.setString(1, this.v.getTitle());
            prep.setString(2, this.v.getThumbnail());
            prep.setString(3, this.v.getCode());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            this.v.setID(tableKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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
    }

    @Override
    public void update() {
        try {
            Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = this.connection.prepareStatement(this.updateQuery());
            prep.setString(1, this.v.getTitle());
            prep.setString(2, this.v.getThumbnail());
            prep.setString(3, this.v.getCode());
            prep.setInt(4, this.v.getID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Video findById(int id) {
        return null;
    }


    @Override
    public String insertQuery() {
        return super.insertQuery() + "(title, thumbnail, code) VALUES(?,?,?)";
    }

    @Override
    public String updateQuery() {
        return super.updateQuery() + "title = (?), thumbnail  = (?), code = (?) WHERE id_video = (?)";
    }

    @Override
    public String deleteQuery() {
        return super.deleteQuery() + " WHERE id_video = (?)";
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
