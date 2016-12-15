package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Playlist;
import views.PlaylistListViewCell;

import java.util.List;


public class PlaylistController extends Controller {

    @FXML JFXListView<Playlist> list;
    private ObservableList<Playlist> playlistObservableList;
    private List<Playlist> playlists;

    public PlaylistController() {
        this.list = new JFXListView<Playlist>();
        this.playlistObservableList = FXCollections.observableArrayList(this.app.getUser().getPlaylists());
    }

    @FXML
    public void initialize() {
        this.list.setItems(this.playlistObservableList);
        this.list.setCellFactory(param -> new PlaylistListViewCell(app.getAppController()));
    }
}
