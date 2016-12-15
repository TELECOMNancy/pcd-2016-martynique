package db;

import models.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.*;

import app.SceneManager;
import controllers.SuggestionController.Flag;


public class SuggestionDB {

    public static final String TABLE_SUGGESTIONS = "Suggestions";
    public static final String TABLE_TAGS = "Tags";

    public static final String PATH_JSON = "/json/";

    private static String generateRequest(HashMap<Flag, String> flags){
    	String WHERE = "";
    	String JOIN = "";
    	for(Flag s : flags.keySet()){
    		if(!WHERE.equals(""))
        		WHERE += "AND ";
		
			if(s == Flag.TAG){
				WHERE += "Tags.tag = '" + flags.get(s)+"' ";
				JOIN = "INNER JOIN Tags ON Suggestions.id = Tags.id_video ";
			}
			if(s == Flag.LENGTH)
    			WHERE += flags.get(s)+" ";
    	}
    	
    	String request = "";
    	request += "SELECT code FROM " + SuggestionDB.TABLE_SUGGESTIONS + " ";
    	request += JOIN;
    	request += "WHERE " + WHERE;

    	return request;
    }

    public static List<String> runSuggestionQuery(HashMap<Flag, String> flags) {
        List<String> list = new ArrayList<String>();
        try {
            Statement st  = ConnectionDB.getInstance().createStatement();
            ResultSet rs = st.executeQuery(generateRequest(flags));

            while(rs.next())
                list.add(rs.getString("code"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public static void insertSuggestion(Suggestion suggestion){
    	String query = "INSERT INTO "+SuggestionDB.TABLE_SUGGESTIONS+" (code, length) VALUES (?, ?);";
        try {
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, suggestion.getCode());
            prep.setInt(2, suggestion.getLength());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            suggestion.setID(new IntegerID(tableKeys.getInt(1)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertTag(Tag tag){
    	String query = "INSERT INTO "+SuggestionDB.TABLE_TAGS+" (id_video, tag) VALUES (?, ?);";
        try {
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, Integer.parseInt(tag.getIDVideo()));
            prep.setString(2, tag.getTag());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            tag.setID(new IntegerID(tableKeys.getInt(1)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeTables(){
    	File f = new File(SuggestionDB.class.getResource("/suggestions/").getFile());
    	File[] matchingFiles = f.listFiles(new FilenameFilter(){
    		public boolean accept(File dir, String name){
    			return name.startsWith("sug") && name.endsWith(".json");
    		}
    	});
    	
    	for(int i=0;i<matchingFiles.length;i++){
	    	JSONTokener tokener;
			try {
				tokener = new JSONTokener(SuggestionDB.class.getResource("/suggestions/"+matchingFiles[i].getName()).openStream());
				JSONObject root = new JSONObject(tokener);
		    	JSONArray sug = root.getJSONArray("sug");
		    	for(int j=0;j<sug.length();j++){
		    		String code = sug.getJSONObject(j).getString("code");
		    		int length = sug.getJSONObject(j).getInt("length");
		    		JSONArray tags = sug.getJSONObject(j).getJSONArray("tags");
		    		Suggestion curVideo = new Suggestion(code, length);
		    		insertSuggestion(curVideo);
		    		for(int k=0;k<tags.length();k++){
		    			String curTagValue = tags.getString(k);
		    			Tag curTag = new Tag(new IntegerID(Integer.parseInt(curVideo.getID())), curTagValue);
		    			insertTag(curTag);
		    		}
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    	
    public static void createTable() {
    	
    	String createString1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SUGGESTIONS +  " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "code varchar(255) NOT NULL, " +
                "length INTEGER NOT NULL)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS " + SuggestionDB.TABLE_SUGGESTIONS);
            st.executeUpdate(createString1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String createString2 = "CREATE TABLE IF NOT EXISTS " + TABLE_TAGS +  " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_video INTEGER NOT NULL, " +
                "tag varchar(255) NOT NULL)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS "+SuggestionDB.TABLE_TAGS);
            st.executeUpdate(createString2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initializeTables();
    }
}
