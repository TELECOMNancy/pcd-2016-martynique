package db;

import models.Suggestion;
import models.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controllers.SuggestionController.Flag;


public class SuggestionDB extends ModelDB<Suggestion> {

    private static final String TABLE = "Suggestions";
    private static final String TABLE_JOIN = "Tags";

    public SuggestionDB() {
        super(TABLE);
    }
    
    private String generateRequest(HashMap<Flag, String> flags){
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
    	request += "SELECT code FROM " + this.TABLE + " ";
    	request += JOIN;
    	request += "WHERE " + WHERE;

    	System.out.println(request);
    	return request;
    }

    public List<String> runSuggestionQuery(HashMap<Flag, String> flags) {
        List<String> list = new ArrayList<String>();
        try {
            Statement st  = this.connection.createStatement();
            ResultSet rs = st.executeQuery(generateRequest(flags));

            while(rs.next())
                list.add(rs.getString("code"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void createTable() {
        String createString1 = "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "code varchar(255) NOT NULL, " +
                "length INTEGER NOT NULL)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String createString2 = "CREATE TABLE IF NOT EXISTS " + TABLE_JOIN +  " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_video INTEGER NOT NULL, " +
                "tag varchar(255) NOT NULL)";
        try {
            Statement st = ConnectionDB.getInstance().createStatement();
            st.executeUpdate(createString2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void create() {}

	@Override
	public void update() {}

    @Override
    public Suggestion findById(int id) {
        return null;
    }

    @Override
	public List<Suggestion> all() {
		return null;
	}
}
