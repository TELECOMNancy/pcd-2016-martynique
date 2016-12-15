package models;

/**
 * Represents a video in our app.
 */
public class Video implements Identifiable {

    private ID id;
    private String title;
    private String thumbnail;
    private boolean favorite;

    public Video(String title, String thumb, String code) {
        this.id = new VarcharID(code);
        this.title = title;
        this.thumbnail = thumb;
        this.favorite = false;
    }

    @Override
    public String getID() {
        return this.id.getValue();
    }

    @Override
    public void setID(ID id) {}

    public String getTitle() {
        return this.title;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public String toString() {
        return "[" + this.getID() + "#Video] " + this.title
                + " - " + (this.isFavorite() ? "FAV" :"");
    }

    public boolean isFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    
    public boolean equals(Object o) {
        if(o != null && o instanceof Video) {
            Video tmp = (Video) o;
            return this.getID().equals(tmp.getID());
        }
        return false;
    }
}
