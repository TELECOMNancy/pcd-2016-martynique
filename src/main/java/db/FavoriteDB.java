package db;

import models.Favorite;
import models.Video;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FavoriteDB extends ModelDB<Favorite> {

    private Favorite f;
    private static final String TABLE = "Favorites";


    public FavoriteDB(Favorite f) {
        super(TABLE);
        this.f = f;
    }

    @Override
    public void create() {
        try {
            Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = this.connection.prepareStatement(this.insertQuery(), Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, this.f.getFavorite().getID());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            this.f.setID(tableKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = this.connection.prepareStatement(this.updateQuery());
            prep.setInt(1, this.f.getFavorite().getID());
            prep.setInt(2, this.f.getID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Favorite findById(int id) {
        Favorite f = null;
        try {
            PreparedStatement prep = ConnectionDB.getInstance().prepareStatement(FavoriteDB.findByIdQuery(TABLE));
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();

            if(rs.next()) {
                f = new Favorite(VideoDB.findById(rs.getInt("id_video")));
                f.setID(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<Favorite> all() {
        List<Favorite> list = new ArrayList<Favorite>();
        try {
            Statement st  = this.connection.createStatement();
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

    @Override
    public String insertQuery() {
        return super.insertQuery() + "(id_video) VALUES(?)";

    }

    @Override
    public String updateQuery() {
        return super.updateQuery() + " SET id_video  = (?) WHERE id_favorite = (?)";
    }

    @Override
    public String deleteQuery() {
        return super.deleteQuery() + " WHERE id_favorite = (?)";
    }

    public static void createTable() {
        String createString = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
