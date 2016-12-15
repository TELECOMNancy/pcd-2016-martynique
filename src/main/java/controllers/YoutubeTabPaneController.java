package controllers;

import app.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

/**
 * Controller managing all tabs on application
 */
public class YoutubeTabPaneController extends Controller {

    @FXML private Tab favoritesTab;
    @FXML private Tab playlistTab;
    @FXML private Tab suggestTab;
    @FXML private Tab localVideosTab;
    @FXML private Tab settingsTab;

    public YoutubeTabPaneController() {
    }

    @FXML
    public void initialize() {
        loadFavorites();
        loadPlaylists();
        loadSuggestion();
        loadLocalVideos();
        loadSettings();
    }

    private void loadFavorites() {
        FXMLLoader loader = SceneManager.getLoader("listView.fxml");
        FavoritesController ctrl = new FavoritesController();
        loader.setController(ctrl);
        this.favoritesTab.setContent(SceneManager.getComponent(loader));
    }

    private void loadPlaylists() {
        FXMLLoader loader = SceneManager.getLoader("listView.fxml");
        PlaylistController ctrl = new PlaylistController();
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
    
    public void loadSettings() {
        FXMLLoader loader = SceneManager.getLoader("settings.fxml");
        SettingsController ctrl = new SettingsController();
        loader.setController(ctrl);
        this.settingsTab.setContent(SceneManager.getComponent(loader));
    }
}
