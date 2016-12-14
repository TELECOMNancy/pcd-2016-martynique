package models;

public class Tag implements Identifiable {

    private ID id;
    private int idVideo;
    private String tag;

    public Tag(int idVideo, String tag){
        this.id = new IntegerID();
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
    public String getID() {
        return this.tag;
    }

    @Override
    public void setID(ID id) {
        this.id = id;
    }

}
