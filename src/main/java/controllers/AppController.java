package controllers;

import app.SceneManager;
import com.google.api.services.youtube.model.SearchResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

import views.WebPlayer;

/**
 * The main Controller of the app
 */
@SuppressWarnings("restriction")
public class AppController {

    @FXML
    private BorderPane root;

    @FXML
    private SearchController searchController;
    
    @FXML
    private void initialize() {
        this.searchController.injectAppController(this);
    }
    
    public void showHome() {
        FXMLLoader loader = SceneManager.getLoader("homepage.fxml");
        this.root.setCenter(SceneManager.getComponent(loader));
    }

    public void showResults(List<SearchResult> results) {
        FXMLLoader loader = SceneManager.getLoader("results.fxml");
        ResultsController ctrl = new ResultsController(results);
        ctrl.injectAppController(this);
        loader.setController(ctrl);

        this.root.setCenter(SceneManager.getComponent(loader));
    }
    
    public void playWebVideo(String videoID) {
        FXMLLoader loader = SceneManager.getLoader("WebVideoPage.fxml");
        
        WebVideoController ctrl = new WebVideoController(videoID);
        ctrl.injectAppController(this);
        loader.setController(ctrl);
        
        BorderPane bp = (BorderPane) SceneManager.getComponent(loader);
        
        WebPlayer wp = new WebPlayer();
        wp.play(videoID);
        
        bp.setCenter(wp);
        
        this.root.setTop(null);
        this.root.setCenter(bp);
    }
    
    public void goFullScreen() {
        ((Stage) this.root.getScene().getWindow()).setFullScreen(true);
    }
    
    public void quitFullScreen() {
        ((Stage) this.root.getScene().getWindow()).setFullScreen(false);
    }

}
