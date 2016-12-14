package models;

/**
 * Represents a Favorite
 */
public class Favorite implements Identifiable {

    private int id;
    private Video fav;

    public Favorite(Video v) {
        this.fav = v;
    }

    public Video getFavorite() {
        return this.fav;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public String toString() {
        return "[" + this.getID()+ "#Favorite] " + fav;
    }
}
