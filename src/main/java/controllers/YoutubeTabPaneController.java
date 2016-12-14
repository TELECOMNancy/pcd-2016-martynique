package controllers;

import app.SceneManager;
import db.FavoriteDB;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import models.Favorite;
import views.YoutubeTabPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller managing all tabs on application
 */
public class YoutubeTabPaneController extends Controller {

    @FXML
    private Tab favoritesTab;
    @FXML
    private Tab playlistTab;
    @FXML
    private Tab suggestTab;

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

        this.suggestTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(suggestTab.isSelected())
                    appController.showSuggestion();
            }
        });
    }

    private void showFavorites() {
        List<Favorite> list = new ArrayList<Favorite>();
        System.out.println(list);
        FavoriteDB db = new FavoriteDB(null);
        list = db.all();

        FXMLLoader loader = SceneManager.getLoader("results.fxml");
        FavoritesController ctrl = new FavoritesController(list);
        ctrl.injectAppController(this.appController);
        loader.setController(ctrl);
        this.favoritesTab.setContent(SceneManager.getComponent(loader));
    }

}
