package db;

import models.Favorite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class FavoriteDB extends ModelDB<Favorite> {

    private Favorite f;

    public FavoriteDB(Favorite f) {
        super("Favorites");
        this.f = f;
    }

    @Override
    public void create() {
        try {
            Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = this.connection.prepareStatement(this.insertQuery(), Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, this.f.getFavorite().getID());
            this.f.setID(prep.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = this.connection.prepareStatement(this.insertQuery());
            prep.setInt(1, this.f.getFavorite().getID());
            prep.setInt(2, this.f.getID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Favorite> all() {
        List<Favorite> list = new ArrayList<Favorite>();
        try {
            Statement st  = this.connection.createStatement();
            ResultSet rs = st.executeQuery(allQuery());

            while(rs.next()){
                //Favorite tmp = new Favorite(rs.getInt("id_video"))
                Favorite tmp = new Favorite(null);
                tmp.setID(rs.getInt("id_favorite"));
                list.add(tmp);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void delete() {
        try {
            Statement st  = this.connection.createStatement();
            PreparedStatement prep = this.connection.prepareStatement(this.insertQuery());
            prep.setInt(1, this.f.getID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String insertQuery() {
        return super.insertQuery() + "(id_video) VALUES(?)";
    }

    public String updateQuery() {
        return super.updateQuery() + " SET id_video  = (?) WHERE id_favorite = (?)";
    }

    public String deleteQuery() {
        return super.deleteQuery() + " WHERE id_favorite = (?)";
    }
}
