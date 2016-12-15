package views;

import com.jfoenix.controls.JFXListCell;
import controllers.AppController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Video;

import java.io.IOException;

/**
 * Main listCell of the app (for favorites and searchResults
 */
public class VideoListViewCell extends JFXListCell<Video> {

    @FXML private GridPane cell;
    @FXML private ImageView thumbnail;
    @FXML private Label title;
    @FXML private Label isFavorite;
    private AppController ctrl;


    public VideoListViewCell(AppController ctrl) {
        this.ctrl = ctrl;
    }


    public void updateItem(Video value, boolean empty) {
        super.updateItem(value, empty);

        if(empty || value == null) {
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
            if(ctrl.getUser().hasFavorite(value))
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
                    if(ctrl.toggleFavorite(value))
                        isFavorite.getStyleClass().add("favorite");
                    else
                        isFavorite.getStyleClass().remove("favorite");
                }
            });
        }

        this.setText(null);
        this.setGraphic(this.cell);
    }
}

