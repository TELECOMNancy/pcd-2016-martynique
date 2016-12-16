package controllers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.Playlist;
import views.PlaylistListViewCell;

import java.util.List;


public class PlaylistsController extends TabController {

    private JFXListView<Playlist> list;
    private ObservableList<Playlist> playlistObservableList;
    private List<Playlist> playlists;
    @FXML private Label addPlaylist;

    public PlaylistsController() {
        this.list = new JFXListView<Playlist>();
        this.playlistObservableList = FXCollections.observableArrayList(this.app.getUser().getPlaylists());
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.list.setItems(this.playlistObservableList);
        this.list.setCellFactory(param -> new PlaylistListViewCell(app.getAppController()));
        this.tab.setCenter(this.list);

        this.addPlaylist.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JFXDialog dialog = new JFXDialog();
                //dialog.setDialogContainer();
            }
        });
    }

    @Override
    public String getTabName() {
        return "Playlists";
    }
}
