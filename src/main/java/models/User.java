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

    private List<Favorite> favorites;

    public User() {
        this.favorites = new ArrayList<Favorite>();
    }

    public void addFavorite(Favorite f) {
        this.favorites.add(f);
    }

    public List<Favorite> getFavorites() {
        return this.favorites;
    }

    public void removeFavorite(Favorite f) {
        this.favorites.remove(f);
        this.setChanged();
        this.notifyObservers();
    }

}
