package views;

import com.jfoenix.controls.*;
import controllers.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import models.Playlist;
import models.Video;

import java.io.IOException;
import java.util.List;

/**
 * Main listCell of the app (for favorites and searchResults
 */
public class VideoListViewCell extends JFXListCell<Video> {

    @FXML private GridPane cell;
    @FXML private ImageView thumbnail;
    @FXML private Label title;
    @FXML private Label isFavorite;
    @FXML private Label addToPlaylist;
    private AppController ctrl;


    public VideoListViewCell(AppController ctrl) {
        this.ctrl = ctrl;
    }


    public void updateItem(Video value, boolean empty) {
        super.updateItem(value, empty);

        if (empty || value == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/videoCell.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.title.setText(value.getTitle());
            this.thumbnail.setImage(new Image(value.getThumbnail()));
            if (ctrl.getUser().hasFavorite(value))
                this.isFavorite.getStyleClass().add("favorite");


            this.thumbnail.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ctrl.playWebVideo(value);
                }
            });

            this.title.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ctrl.playWebVideo(value);
                }
            });
            this.isFavorite.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (ctrl.toggleFavorite(value))
                        isFavorite.getStyleClass().add("favorite");
                    else
                        isFavorite.getStyleClass().remove("favorite");
                }
            });

            this.addToPlaylist.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pushInPlaylist(value);
                }
            });
        }
        this.setText(null);
        this.setGraphic(this.cell);
    }


    private void pushInPlaylist(Video v) {
        JFXDialog dialog = new JFXDialog();
        JFXDialogLayout layout = new JFXDialogLayout();
        //layout.setStyle("-fx-background-color: rgb(34, 34, 34)");
        layout.setHeading(new Label("Ajouter à une playlist"));

        ObservableList<Playlist> choices = FXCollections.observableArrayList(ctrl.getUser().getPlaylists());
        JFXComboBox combo = new JFXComboBox(choices);
        combo.setPromptText("Rock, Soundtracks ...");

        combo.setCellFactory(param -> new PlaylistComboCell());
        combo.setEditable(false);
        layout.setBody(combo);

        JFXButton create = new JFXButton("Ajouter");
        layout.setActions(create);

        dialog.setContent(layout);
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.setDialogContainer(ctrl.getContainer());
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ctrl.pushVideoToPlaylist((Playlist) combo.getSelectionModel().getSelectedItem(), v);
                dialog.close();
            }
        });
        //dialog.show();
        dialog.setFocusTraversable(true);
    }

}

