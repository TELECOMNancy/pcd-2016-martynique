package controllers;

import app.SceneManager;
import javafx.event.Event;
import javafx.event.EventHandler;
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

    public YoutubeTabPaneController() {
    }

    @FXML
    public void initialize() {
        loadFavorites();
        loadSuggestion();
        loadLocalVideos();
        
        this.localVideosTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(localVideosTab.isSelected())
                    System.out.println("hello");
                    //a remplacer par un file chooser
                    //app.getAppController().playLocalVideo("");
            }
        });
    }

    private void loadFavorites() {
        FXMLLoader loader = SceneManager.getLoader("videosListView.fxml");
        FavoritesController ctrl = new FavoritesController();
        loader.setController(ctrl);
        this.favoritesTab.setContent(SceneManager.getComponent(loader));
    }
    
    public void loadSuggestion() {
        FXMLLoader loader = SceneManager.getLoader("suggestion.fxml");
        SuggestionController ctrl = new SuggestionController();
        loader.setController(ctrl);
        this.suggestTab.setContent(SceneManager.getComponent(loader));
    }
    
    private void loadLocalVideos() {
        FXMLLoader loader = SceneManager.getLoader("videosListView.fxml");
        LocalListController ctrl = new LocalListController();
        loader.setController(ctrl);
        this.localVideosTab.setContent(SceneManager.getComponent(loader));
    }
}
