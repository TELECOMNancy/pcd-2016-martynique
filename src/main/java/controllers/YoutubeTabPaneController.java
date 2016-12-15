package controllers;

import app.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import models.Playlist;

/**
 * Controller managing all tabs on application
 */
public class YoutubeTabPaneController extends Controller {

    @FXML private Tab favoritesTab;
    @FXML private Tab playlistTab;
    @FXML private Tab suggestTab;
    @FXML private Tab localVideosTab;

    public YoutubeTabPaneController() {
        favoritesTab = new Tab();
        playlistTab = new Tab();
        suggestTab = new Tab();
        localVideosTab = new Tab();
    }

    @FXML
    public void initialize() {
        loadFavorites();
        loadSuggestion();
        loadLocalVideos();
        loadPlaylists();
    }

    private void loadFavorites() {
        FXMLLoader loader = SceneManager.getLoader("listView.fxml");
        FavoritesController ctrl = new FavoritesController();
        loader.setController(ctrl);
        this.favoritesTab.setContent(SceneManager.getComponent(loader));
    }

    private void loadPlaylists() {
        FXMLLoader loader = SceneManager.getLoader("listView.fxml");
        PlaylistsController ctrl = new PlaylistsController();
        loader.setController(ctrl);
        this.playlistTab.setContent(SceneManager.getComponent(loader));
    }
    
    public void loadSuggestion() {
        FXMLLoader loader = SceneManager.getLoader("suggestion.fxml");
        SuggestionController ctrl = new SuggestionController();
        loader.setController(ctrl);
        this.suggestTab.setContent(SceneManager.getComponent(loader));
    }
    
    private void loadLocalVideos() {
        FXMLLoader loader = SceneManager.getLoader("listView.fxml");
        LocalListController ctrl = new LocalListController();
        loader.setController(ctrl);
        //this.localVideosTab.setContent(SceneManager.getComponent(loader));
    }

    public void showPlaylist(Playlist p) {
        FXMLLoader loader = SceneManager.getLoader("playlistView.fxml");
        PlaylistController ctrl = new PlaylistController(p);
        loader.setController(ctrl);
        //this.playlistTab.setContent(null);
        this.playlistTab.setContent(SceneManager.getComponent(loader));
    }
}
