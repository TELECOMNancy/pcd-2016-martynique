package controllers;

import app.SceneManager;
import com.google.api.services.youtube.model.SearchResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import models.Video;

import java.util.List;

/**
 * The main Controller of the app
 */
public class AppController {

    @FXML
    private BorderPane root;

    @FXML
    private SearchController searchController;
    

    @FXML
    private void initialize() {
        this.searchController.injectAppController(this);
    }

    public void showResults(List<Video> results) {
        FXMLLoader loader = SceneManager.getLoader("results.fxml");
        ResultsController ctrl = new ResultsController(results);
        ctrl.injectAppController(this);
        loader.setController(ctrl);

        this.root.setCenter(SceneManager.getComponent(loader));
    }

}
