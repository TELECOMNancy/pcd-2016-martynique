package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.sg.prism.NGShape;
import models.IntegerID;
import models.Playlist;
import models.VarcharID;
import models.Video;

public class PlaylistDB{

    private static final String TABLE = "Playlist";

    public static void create(Playlist playlist) {
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(insertQuery(), Statement.RETURN_GENERATED_KEYS);
            //prep.setString(1, playlist.getID());
            prep.setString(1, playlist.getName());
            prep.executeUpdate();
            
            System.out.println("Size "+playlist.getVideoList().size());
            
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            playlist.setID(new IntegerID(tableKeys.getInt(1)));
            

            	for(Video i : playlist.getVideoList()){
            		System.out.println("link created");
            		PlaylistLinkDB.createLink(playlist,i);
            	}


            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   public static Playlist importVideo(int id_playlist){
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
        	   Video matchVideo = VideoDB.findById(idVideoOfPlaylist.get(i));
        	   listVideo.add(matchVideo);
           }
           playlist.setVideoList(listVideo);
           
 
       return playlist;
   }
    
    public static Playlist getPlaylist(int id_playlist){
        PreparedStatement prep;
        Playlist playlist=null;
 	try {
 		prep = ConnectionDB.getInstance().prepareStatement(whereQuery());
 		prep.setInt(1, id_playlist);
         ResultSet rs = prep.executeQuery();

         playlist = new Playlist(rs.getString("title"));
         playlist.setID(new IntegerID(rs.getInt("id")));
         
         rs.close();
 	} catch (SQLException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}
        return playlist;
    }
    
    public static void dropTable(){
        String createString = "DROP TABLE IF EXISTS "+TABLE;
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String whereQuery() {
        return "SELECT * FROM " + TABLE + " WHERE id = ?"; 
    }
    
    public static String insertQuery() {
        return ModelDB.insertQuery(TABLE) + "(title) VALUES(?)";
    }

	
    public static void createTable() {
        String createString = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
        		"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"title varchar(255) NOT NULL UNIQUE)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Playlist> all() {
        List<Playlist> list = new ArrayList<Playlist>();
        try {
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(ModelDB.allQuery(TABLE));
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                Playlist p = new Playlist(rs.getString("title"));
                list.add(p);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
