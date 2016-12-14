package db;

import models.Playlist;
import models.Video;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlaylistLinkDB {
	
	private static final String TABLE = "PlaylistLink";


	
	public static void createLink(Playlist play, Video vid) {
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(insertQuery(), Statement.RETURN_GENERATED_KEYS);

            prep.setString(1, Integer.toString(play.getID()));
            prep.setString(2, Integer.toString(vid.getID()));
            
            prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
		
    public static void createTable(){
        String junction_table = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "id_playlist INTEGER , " +
                "id_video INTEGER, " +
                "CONSTRAINT id_link PRIMARY KEY (id_playlist, id_video)," +
                "CONSTRAINT FK_video FOREIGN KEY (id_video) REFERENCES Videos (id),"+
                "CONSTRAINT FK_playlist FOREIGN KEY (id_playlist) REFERENCES PlaylistLink (id)"+
                ")";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(junction_table);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public static ArrayList<String> getVideoIdOfPlaylist(int playlist_id){
    	ArrayList<String> videoIdOfPlaylist = new ArrayList<String>();
        PreparedStatement prep;
        try {
			prep = ConnectionDB.getInstance().prepareStatement(whereQuery());
			prep.setInt(1, playlist_id);
	        ResultSet rs = prep.executeQuery();
	        while(rs.next()){
	        	String id_video = rs.getString("id_video");
	        	//System.out.println(id_video);
	        	videoIdOfPlaylist.add(id_video);
	        }
	        rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return videoIdOfPlaylist;
    	
    }
    

    public static String insertQuery() {
        return "INSERT INTO " + TABLE + "(id_playlist, id_video) VALUES(?,?)";
    }


    public static String updateQuery() {
        return "UPDATE  " + TABLE + "title = (?), thumbnail  = (?), code = (?) WHERE id_video = (?)";
    }


    public static String deleteQuery() {
        return "DELETE FROM " + TABLE  + " WHERE id_video = (?)";
    }
    
    public static String allQuery() {
        return "SELECT * FROM " + TABLE;
    }
    
    public static String whereQuery() {
        return "SELECT * FROM " + TABLE + " WHERE id_playlist = ?";
    }

}
