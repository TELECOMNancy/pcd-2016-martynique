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
        if(!this.hasFavorite(f.getFavorite()))
            this.favorites.add(f);
    }

    public List<Favorite> getFavorites() {
        return this.favorites;
    }

    public boolean hasFavorite(Video v) {
        return this.getFavorites().contains(new Favorite(v));
    }

    public boolean hasFavorite(Favorite f) {
        return this.getFavorites().contains(f);
    }

    public void removeFavorite(Favorite f) {
        this.favorites.remove(f);
        this.setChanged();
        this.notifyObservers();
    }

}
