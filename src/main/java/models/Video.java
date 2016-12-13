package models;

/**
 * Represents a vidoo in our app.
 */
public class Video implements Identifiable {

    private int id;
    private String title;
    private String thumbnail;
    private String code;

    public Video(String title, String thumb, String code) {
        this.title = title;
        this.thumbnail = thumb;
        this.code = code;
    }


    public int getID() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public String getCode() {
        return this.code;
    }

}
