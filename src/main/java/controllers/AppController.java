package controllers;

import app.SceneManager;
import app.WebPlayer;

import com.jfoenix.controls.JFXSpinner;
import db.PlaylistDB;
import db.VideoDB;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Playlist;
import models.User;
import models.Video;
import utils.Search;
import utils.YTD;

import java.io.File;
import java.util.List;

/**
 * The main Controller of the app
 */
public class AppController {

    @FXML private StackPane container;
    @FXML private BorderPane root;
    @FXML private SearchController searchController;
    @FXML private YoutubeTabPaneController youtubeTabPaneController;
    @FXML private ResultsController resultsController;

    //private BorderPane homepage;
    private StackPane loading;
    private User user;
    private WebPlayer wp;

    public AppController(User user) {
        this.user = user;
        this.loading = new StackPane();
        this.loading.getStyleClass().add("loading");
        this.loading.getChildren().add(new JFXSpinner());
    }   

    @FXML
    private void initialize() {
        wp = new WebPlayer();
        /*
        if(this.homepage == null) {
            FXMLLoader loader = SceneManager.getLoader("homepage.fxml");
            //resetRoot();
            StackPane p = (StackPane) SceneManager.getComponent(loader);
            System.out.println(p.getChildren().get(0));
            this.homepage = (BorderPane)  p.getChildren().get(0);
        }
        */
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
        BorderPane bp = (BorderPane) ((StackPane) SceneManager.getComponent(loader)).getChildren().get(0);
        
        this.root.setTop(bp.getTop());
        this.root.setCenter(bp.getCenter());
    }

    public void search(String query) {
        this.root.setCenter(this.loading);
        Task<Void> task = new Task<Void>() {
            Search search = new Search(query);
            @Override
            protected Void call() throws Exception {
                search.executeApiRequest();
                return null;
            }
            @Override protected void succeeded() {  showResults(search.getVideoList());  }
        };
        new Thread(task).start();
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

    public void playLocalVideo(File videoID) {
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
                    user.addDownloadedVideo(v);
                return null;
            }
        };
        
        task.setOnFailed(e -> {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Download Error");
            alert.setHeaderText("Download failed!");
            alert.setContentText("The video : \'" + v.getTitle() +"' couldn't be downloaded.\n"
                    + "It is possible the video is protected.");

            alert.showAndWait();
        });
        
        task.setOnSucceeded(e -> {            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Download Success");
            alert.setHeaderText("Download Succeeded!");
            alert.setContentText("The video : \'" + v.getTitle() +"' was successfully downloaded.\n"
                    + "You can now watch it offline by going in the Downloaded tab.");

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

    public StackPane getContainer() {
        return this.container;
    }

    public void showPlayList(Playlist p) {
        this.youtubeTabPaneController.showPlaylist(p);
    }

    public void createPlaylist(Playlist p) {
        this.user.addPlaylist(p);
        PlaylistDB.create(p);
    }

    public void pushVideoToPlaylist(Playlist selectedItem, Video v) {
        this.user.addVideoToPlaylist(v, selectedItem);
    }
}
