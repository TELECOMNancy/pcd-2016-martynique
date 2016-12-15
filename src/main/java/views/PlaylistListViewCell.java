package views;

import com.jfoenix.controls.JFXListCell;
import controllers.AppController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import models.Playlist;

import java.io.IOException;

/**
 * Main listCell of the app (for favorites and searchResults
 */
public class PlaylistListViewCell extends JFXListCell<Playlist> {

    @FXML private GridPane cell;
    @FXML private ImageView thumbnail;
    @FXML private Label playlist;
    private AppController ctrl;


    public PlaylistListViewCell(AppController ctrl) {
        this.ctrl = ctrl;
    }


    public void updateItem(Playlist value, boolean empty) {
        super.updateItem(value, empty);

        if(empty || value == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playlistCell.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.playlist.setText(value.getName());
            this.thumbnail.setImage(new Image(value.getRandomThumbnail()));

            /*this.thumbnail.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ctrl.playWebVideo(value.getID());
                }
            });

            this.title.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ctrl.playWebVideo(value.getID());
                }
            });
            this.isFavorite.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(ctrl.toggleFavorite(value))
                        isFavorite.getStyleClass().add("favorite");
                    else
                        isFavorite.getStyleClass().remove("favorite");
                }
            });*/
        }

        this.setText(null);
        this.setGraphic(this.cell);
    }
}

