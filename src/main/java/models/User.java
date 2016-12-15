package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This class defines a user of the app.
 * The user has some favorites videos, playlists...
 *
 * @author Mcdostone
 */
public class User extends Observable {

    private List<Video> favorites;

    public User() {
        this.favorites = new ArrayList<Video>();
    }

    public void addFavorite(Video v) {
        if(!this.hasFavorite(v) && v.isFavorite()) {
            this.favorites.add(v);
            this.setChanged();
            this.notifyObservers("add-favorite");
        }
    }

    public List<Video> getFavorites() {
        return this.favorites;
    }

    public boolean hasFavorite(Video v) {
        return this.getFavorites().contains(v);
    }

    public void removeFavorite(Video f) {
        this.favorites.remove(f);
        this.setChanged();
        this.notifyObservers("remove-favorite");
    }

}
