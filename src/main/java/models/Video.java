package models;

/**
 * Represents a video in our app.
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

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
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

    public String toString() {
        return "[" + this.getID() + "#Video] " +
                (this.title.length() > 10 ? this.title.substring(0, 10) : this.title.substring(0, this.title.length() - 1))
                + " - " + this.code;
    }

    public boolean equals(Object o) {
        if(o != null && o instanceof Video) {
            Video tmp = (Video) o;
            return this.getCode().equals(tmp.getCode());
        }
        return false;
    }

}
