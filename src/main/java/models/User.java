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
    private List<Playlist> playlists;

    public User() {
        this.favorites = new ArrayList<Video>();
        this.playlists = new ArrayList<Playlist>();

        Playlist p = new Playlist("Compil de beauf");
        p.addVideo(new Video("THE LATEST BEST FIGHT Compilation in 2016", "https://i.ytimg.com/vi/RvrOqi2hLmU/default.jpg" , "OeYKnC9E4s4"));
        this.playlists.add(p);
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

    public List<Playlist> getPlaylists() {
        return this.playlists;
    }

    public boolean hasFavorite(Video v) {
        return this.getFavorites().contains(v);
    }

    public void removeFavorite(Video f) {
        this.favorites.remove(f);
        System.out.println(this.favorites);
        this.setChanged();
        this.notifyObservers("remove-favorite");
    }

}
