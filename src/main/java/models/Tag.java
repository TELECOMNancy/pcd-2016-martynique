package models;

public class Tag implements Identifiable {

    private ID id;
    private ID idVideo;
    private String tag;

    public Tag(ID idVideo, String tag){
        this.id = new IntegerID();
    	this.idVideo = idVideo;
    	this.tag = tag;
    }
    
    public String getIDVideo(){
    	return this.idVideo.getValue();
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
