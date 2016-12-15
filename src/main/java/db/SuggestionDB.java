package db;

import models.*;

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

    	System.out.println(request);
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
    	String query = "INSERT INTO "+SuggestionDB.TABLE_SUGGESTIONS+" (code, length) VALUES ((?), (?));";
        try {
            // Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, suggestion.getID());
            prep.setInt(2, suggestion.getLength());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertTag(Tag tag){
    	String query = "INSERT INTO "+SuggestionDB.TABLE_TAGS+" (id_video, tag) VALUES ((?), (?));";
        try {
            // Statement st = this.connection.createStatement();
            PreparedStatement prep;
            prep = ConnectionDB.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, tag.getIDVideo());
            prep.setString(2, tag.getTag());
            prep.executeUpdate();
            ResultSet tableKeys = prep.getGeneratedKeys();
            tableKeys.next();
            tag.setID(new IntegerID(tableKeys.getInt(1)));
			System.out.println(tag.getID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeTables(){
    	/*
    	Suggestion video1 = new Suggestion("_yEHa6dIKgg", 14);
    	Suggestion video2 = new Suggestion("-X49VQgi86E", 12);
    	Suggestion video3 = new Suggestion("8RFKi7JcuFA", 10);
    	insertSuggestion(video1);
    	insertSuggestion(video2);
    	insertSuggestion(video3);
    	Tag tag1 = new Tag(video1.getID(), "sciences");
    	Tag tag2 = new Tag(video2.getID(), "sciences");
    	Tag tag3 = new Tag(video3.getID(), "histoire");
    	Tag tag4 = new Tag(video2.getID(), "mathematiques");
    	insertTag(tag1);
    	insertTag(tag2);
    	insertTag(tag3);
    	insertTag(tag4);
    	*/

    	JSONTokener tokener;
		try {
			tokener = new JSONTokener(SuggestionDB.class.getResource("/suggestions/sug1.json").openStream());
			JSONObject root = new JSONObject(tokener);
			System.out.println(root);
	    	JSONArray sug = root.getJSONArray("sug");
	    	for(int i=0;i<sug.length();i++){
	    		String code = sug.getJSONObject(i).getString("code");
	    		int length = sug.getJSONObject(i).getInt("length");
	    		JSONArray tags = sug.getJSONObject(i).getJSONArray("tags");
	    		Suggestion curVideo = new Suggestion(code, length);
	    		insertSuggestion(curVideo);
	    		for(int j=0;j<tags.length();j++){
	    			String curTagValue = tags.getString(j);
	    			Tag curTag = new Tag(curVideo.getID(), curTagValue);
	    			insertTag(curTag);
	    		}
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    	
    public static void createTable() {
    	
    	String createString1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SUGGESTIONS +  " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "code varchar(255) NOT NULL, " +
                "length INTEGER NOT NULL)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            //st.executeUpdate("DROP TABLE " + SuggestionDB.TABLE_SUGGESTIONS);
            st.executeUpdate("DROP TABLE "+TABLE_SUGGESTIONS);
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
            //st.executeUpdate("DROP TABLE " + SuggestionDB.TABLE_TAGS);
            st.executeUpdate("DROP TABLE "+TABLE_TAGS);
            st.executeUpdate(createString2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        initializeTables();
    }
}
