package controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import models.Playlist;
import views.PlaylistListViewCell;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class PlaylistsController extends TabController implements Observer {

    private JFXListView<Playlist> list;
    private ObservableList<Playlist> playlistObservableList;
    private List<Playlist> playlists;
    @FXML private Label addPlaylist;


    public PlaylistsController() {
        this.app.getUser().addObserver(this);
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

                JFXDialogLayout layout = new JFXDialogLayout();
                layout.setHeading(new Label("Créer une playlist"));
                JFXTextField field = new JFXTextField();
                field.setFocusColor(Color.rgb(229, 45, 39));
                layout.setBody(field);
                JFXButton create = new JFXButton("Créer");
                field.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode().equals(KeyCode.ENTER)) {
                            createPlaylist(field.getText());
                            dialog.close();
                        }
                    }
                });
                layout.setActions(create);

                dialog.setContent(layout);
                dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
                dialog.setDialogContainer(app.getAppController().getContainer());
                create.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        createPlaylist(field.getText());
                        dialog.close();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public String getTabName() {
        return "Playlists";
    }

    private void createPlaylist(String name) {
        this.app.getAppController().createPlaylist(new Playlist(name));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("create-playlist")) {
            this.playlistObservableList.clear();
            this.playlistObservableList.addAll(app.getUser().getPlaylists());
        }
    }
}
