package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Playlist;
import models.Video;

public class PlaylistDB{

    private static final String TABLE = "Playlist";

    public void create(Playlist playlist) {
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(ModelDB.insertQuery(TABLE), Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, playlist.getID());
            prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   /*public static Playlist importVideo(int id_playlist){
       	   List<Video> listVideo = new ArrayList<Video>();
       
    	   Playlist playlist = getPlaylist(id_playlist);
    	   if(playlist == null)
			try {
				throw new Exception("Pas de playlist avec cet id");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           ArrayList<String> idVideoOfPlaylist = PlaylistLinkDB.getVideoIdOfPlaylist(id_playlist);


           for(int i=0;i<idVideoOfPlaylist.size();i++){
        	   Video matchVideo = VideoDB.findById(Integer.parseInt(idVideoOfPlaylist.get(i)));
        	   listVideo.add(matchVideo);
           }
           playlist.setVideoList(listVideo);
           
 
       return playlist;
   }*/

    public static String whereQuery() {
        return "SELECT * FROM " + TABLE + " WHERE id_playlist = ?"; 
    }

	
    public static void createTable() {
        String createString = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "title varchar(255) PRIMARY KEY)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
