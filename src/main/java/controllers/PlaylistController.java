package controllers;

import app.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import models.Playlist;


public class PlaylistController extends Controller {

    @FXML BorderPane playlistPane;
    @FXML Label namePlaylist;
    private Playlist playlist;

    public PlaylistController(Playlist p) {
        this.playlist = p;
    }

    @FXML
    public void initialize() {
        this.namePlaylist.setText(this.playlist.getName());
        FXMLLoader loader = SceneManager.getLoader("listView.fxml");
        SetListController rc = new SetListController(this.playlist.getVideoList());
        loader.setController(rc);
        this.playlistPane.setCenter(SceneManager.getComponent(loader));
    }
}
