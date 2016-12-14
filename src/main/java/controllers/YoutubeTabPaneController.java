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
        this.favoritesTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(favoritesTab.isSelected())
                    showFavorites();
            }
        });

        FXMLLoader loader = SceneManager.getLoader("results.fxml");
        FavoritesController ctrl = new FavoritesController();
        loader.setController(ctrl);
        this.favoritesTab.setContent(SceneManager.getComponent(loader));

        this.suggestTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(suggestTab.isSelected())
                    app.getAppController().showSuggestion();
            }
        });
        
        this.localVideosTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(localVideosTab.isSelected())
                    //a remplacer par un file chooser
                    app.getAppController().playLocalVideo("");
            }
        });
    }

    private void showFavorites() {
    }
}
