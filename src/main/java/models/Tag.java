package models;

public class Tag implements Identifiable {

    private ID id;
    private String idVideo;
    private String tag;

    public Tag(String idVideo, String tag){
        this.id = new IntegerID();
    	this.idVideo = idVideo;
    	this.tag = tag;
    }
    
    public String getIDVideo(){
    	return this.idVideo;
    }
    
    public String getTag(){
    	return this.tag;
    }

    @Override
    public String getID() {
        return this.id.getValue();
    }

    @Override
    public void setID(ID id) {
        this.id = id;
    }

}
