package controllers;

import app.SceneManager;
import db.FavoriteDB;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import models.Favorite;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller managing all tabs on application
 */
public class YoutubeTabPaneController extends Controller {

    @FXML private Tab favoritesTab;
    @FXML private Tab playlistTab;
    @FXML private Tab suggestTab;
    @FXML private Tab localVideosTab;

    public YoutubeTabPaneController() {
        super();
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
        ctrl.injectAppController(this.appController);
        loader.setController(ctrl);
        this.favoritesTab.setContent(SceneManager.getComponent(loader));

        this.suggestTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(suggestTab.isSelected())
                    appController.showSuggestion();
            }
        });
        
        this.localVideosTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(localVideosTab.isSelected())
                    //a remplacer par un file chooser
                    appController.playLocalVideo("");
            }
        });
    }

    private void showFavorites() {
        List<Favorite> list = this.appController.getUser().getFavorites();

        if(this.favoritesTab.getContent() == null) {

        }
    }

}
