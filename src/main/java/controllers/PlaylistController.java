package controllers;

import app.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import models.Playlist;


public class PlaylistController extends Controller {

    //@FXML BorderPane playlistPane;
    //@FXML Label namePlaylist;
    private Playlist playlist;
    @FXML protected BorderPane tab;
    @FXML protected Label tabName;

    public PlaylistController(Playlist p) {
        this.playlist = p;
    }

    @FXML
    public void initialize() {
        if(this.playlist != null) {
            System.out.println(playlist);
            this.tabName.setText(this.playlist.getName());
            FXMLLoader loader = SceneManager.getLoader("listView.fxml");
            SetListController rc = new SetListController(this.playlist.getVideoList());
            loader.setController(rc);
            this.tab.setCenter(SceneManager.getComponent(loader));
        }
    }
}
