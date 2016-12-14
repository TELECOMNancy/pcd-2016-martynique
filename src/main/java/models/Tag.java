package models;

public class Tag implements Identifiable {

    private int id;
    private int idVideo;
    private String tag;

    public Tag(int idVideo, String tag){
    	this.idVideo = idVideo;
    	this.tag = tag;
    }
    
    public int getIDVideo(){
    	return this.idVideo;
    }
    
    public String getTag(){
    	return this.tag;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}
