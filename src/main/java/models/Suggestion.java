package models;

/**
 * Represents a vidoo in our app.
 */
public class Suggestion implements Identifiable {

    private int id;
    private int idVideo;
    private int length;
    private String tag;

    public Suggestion(int idVideo, int length, String tag){
        this.idVideo = idVideo;
        this.length = length;
        this.tag = tag;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public int getIDVideo() {
        return this.idVideo;
    }

    public int getLength() {
        return this.length;
    }

    public String getTag() {
        return this.tag;
    }

}
