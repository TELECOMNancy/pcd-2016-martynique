package controllers;

import app.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Video;
import javafx.stage.Stage;

import javafx.concurrent.Task;

import java.util.List;

import views.WebPlayer;

import utils.YTD;

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
    private YoutubeTabPaneController youtubeTabPaneController;

    @FXML
    private void initialize() {
        this.searchController.injectAppController(this);
        this.youtubeTabPaneController.injectAppController(this);
        wp = new WebPlayer();
    }
    
    private WebPlayer wp;
    
    public void showHome() {
        FXMLLoader loader = SceneManager.getLoader("homepage.fxml");
        this.root.setCenter(SceneManager.getComponent(loader));
    }

    public void showResults(List<Video> results) {
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
        
        wp.play(videoID);
        
        bp.setCenter(wp);
        
        this.root.setTop(null);
        this.root.setCenter(bp);
    }
    
    public void stopWebVideo() {
        ((WebPlayer) ((BorderPane) this.root.getCenter()).getCenter()).stop();
    }
    
    public void download(String ID) {
        // might be interesting not to create a new thread each time
        Task<Void> task = new Task<Void>(){
            //@Overrride
            public Void call() throws Exception {
                YTD.download(ID);
                return null;
            }
        };
        
        task.setOnFailed(e -> {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Download Error");
            alert.setHeaderText("Download failed!");
            alert.setContentText("It is possible the video is protected.");

            alert.showAndWait();
        });
        
        new Thread(task).start();
    }
    
    public void goFullScreen() {
        ((Stage) this.root.getScene().getWindow()).setFullScreen(true);
    }
    
    public void quitFullScreen() {
        ((Stage) this.root.getScene().getWindow()).setFullScreen(false);
    }

}
