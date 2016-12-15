package controllers;

import app.Configuration;
import app.SceneManager;
import views.WebPlayer;
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
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
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
        resetRoot();
        
        BorderPane bp = (BorderPane) SceneManager.getComponent(loader);
        this.root.setTop(bp.getTop());
        this.root.setCenter(bp.getCenter());
    }

    public void showResults(List<Video> results) {
        if(this.resultsController == null) {
            FXMLLoader loader = SceneManager.getLoader("listView.fxml");
            this.resultsController = new ResultsController(results);
            loader.setController(this.resultsController);
            SceneManager.getComponent(loader);
        } else
            this.resultsController.updateResults(results);
        
        this.root.setCenter(this.resultsController.getScene());
    }
    
    public void playWebVideo(Video v) {
        FXMLLoader loader = SceneManager.getLoader("WebVideoPage.fxml");
        
        WebVideoController ctrl = new WebVideoController(v);
        loader.setController(ctrl);
        BorderPane bp = (BorderPane) SceneManager.getComponent(loader);
        
        wp.play(v);
        
        bp.setCenter(wp);
        
        resetRoot();
        this.root.setCenter(bp);
    }

    public void playLocalVideo(String videoID) {
        videoID = Configuration.getInstance().getSavePath() + videoID;
        FXMLLoader loader = SceneManager.getLoader("localPlayer.fxml");
        LocalVideoController ctrl = new LocalVideoController(videoID);
        loader.setController(ctrl);
        resetRoot();
        
        StackPane sp = (StackPane) SceneManager.getComponent(loader);
        sp.prefHeightProperty().bind(this.root.heightProperty());
        sp.prefWidthProperty().bind(this.root.widthProperty());
        
        this.root.setCenter(sp);
    }
    
    public void stopWebVideo() {
        ((WebPlayer) ((BorderPane) this.root.getCenter()).getCenter()).stop();
    }
    
    public void download(Video v) {
        // might be interesting not to create a new thread each time
        Task<Void> task = new Task<Void>(){
            //@Overrride
            public Void call() throws Exception {
                boolean ok = YTD.download(v, app.Configuration.getInstance().getSavePath());
                if(ok)
                    user.addDownloaded(v);
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
        // the cursor would hide sometimes after full screen
        this.root.getScene().setCursor(Cursor.DEFAULT);
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
    
    public Scene getScene() {
        return this.root.getScene();
    }


}
