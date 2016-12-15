package controllers;

import app.SceneManager;
import app.WebPlayer;
import db.VideoDB;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.User;
import models.Video;

import java.util.List;

import utils.YTD;

/**
 * The main Controller of the app
 */
public class AppController {

    @FXML private BorderPane root;
    @FXML private SearchController searchController;
    @FXML private YoutubeTabPaneController youtubeTabPaneController;
    @FXML private ResultsController resultsController;

    private User user;
    private WebPlayer wp;

    public AppController(User user) {
        this.user = user;
    }   

    @FXML
    private void initialize() {
        wp = new WebPlayer();
    }
    

    private void resetRoot() {
        this.root.setCenter(null);
        this.root.setTop(null);
        this.root.setBottom(null);
        this.root.setLeft(null);
        this.root.setRight(null);
    }
    
    public void showHome() {
        FXMLLoader loader = SceneManager.getLoader("homepage.fxml");
        this.root.setTop(null);
        this.root.setBottom(null);
        this.root.setRight(null);
        this.root.setLeft(null);
        this.root.setCenter(SceneManager.getComponent(loader));
    }

    public void showResults(List<Video> results) {
        if(this.resultsController == null) {
            FXMLLoader loader = SceneManager.getLoader("videosListView.fxml");
            this.resultsController = new ResultsController(results);
            loader.setController(this.resultsController);
            SceneManager.getComponent(loader);
        }

        this.root.setCenter(this.resultsController.getScene());
    }
    
    public void showSuggestion() {
    	FXMLLoader loader = SceneManager.getLoader("suggestion.fxml");
    	SuggestionController ctrl = new SuggestionController();
        loader.setController(ctrl);

        this.root.setCenter(SceneManager.getComponent(loader));
    }
    
    public void playWebVideo(String videoID) {
        FXMLLoader loader = SceneManager.getLoader("WebVideoPage.fxml");
        
        WebVideoController ctrl = new WebVideoController(videoID);
        loader.setController(ctrl);
        BorderPane bp = (BorderPane) SceneManager.getComponent(loader);
        
        wp.play(videoID);
        
        bp.setCenter(wp);
        
        resetRoot();
        this.root.setCenter(bp);
    }
    
    public void playLocalVideo(String videoID) {
        videoID = "E:/workspace/pcd-2016-martynique/savedVideos/Westworld.mp4";
        FXMLLoader loader = SceneManager.getLoader("localPlayer.fxml");
        LocalVideoController ctrl = new LocalVideoController(videoID);
        loader.setController(ctrl);
        resetRoot();
        this.root.setCenter(SceneManager.getComponent(loader));
    }
    
    public void stopWebVideo() {
        ((WebPlayer) ((BorderPane) this.root.getCenter()).getCenter()).stop();
    }
    
    public void download(String ID) {
        // might be interesting not to create a new thread each time
        Task<Void> task = new Task<Void>(){
            //@Overrride
            public Void call() throws Exception {
                YTD.download(ID, app.Configuration.getInstance().getSavePath());
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
    
    public void goFullScreen(VideoController ctrl) {
        ((Stage) this.root.getScene().getWindow()).setFullScreen(true);
        this.root.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            //@Override
            public void handle(KeyEvent event) {
                ctrl.quitFullScreen();
            }
        });
    }
    
    public void quitFullScreen() {
        ((Stage) this.root.getScene().getWindow()).setFullScreen(false);
    }

    public User getUser() {
        return this.user;
    }

    public boolean toggleFavorite(Video value) {
        value.setFavorite(!value.isFavorite());
        if(value.isFavorite())
            this.user.addFavorite(value);
        else
            this.user.removeFavorite(value);

        VideoDB.setFavorite(value);
        return value.isFavorite();
    }
    
    public void hideCursor() {
        this.root.getScene().setCursor(Cursor.NONE);
    }
    
    public void showCursor() {
        this.root.getScene().setCursor(Cursor.DEFAULT);
    }
}
