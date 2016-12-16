package views;

import controllers.AppController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Video;

import java.io.File;
import java.io.IOException;


public class DownloadedVideoCell extends ListCell<File> {

        @FXML private GridPane cell;
        @FXML private ImageView thumbnail;
        @FXML private Label title;
        @FXML private Label isFavorite;
        private AppController ctrl;


        public DownloadedVideoCell(AppController ctrl) {
            this.ctrl = ctrl;
        }


        public void updateItem(File value, boolean empty) {
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

                this.title.setText(value.getName());
                this.cell.getChildren().remove(this.isFavorite);
                //this.thumbnail.setImage(new Image(value.getThumbnail()));
                /*if(ctrl.getUser().hasFavorite(value))
                    this.isFavorite.getStyleClass().add("favorite");*/


                this.thumbnail.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ctrl.playLocalVideo(value);
                    }
                });

                this.title.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ctrl.playLocalVideo(value);
                    }
                });
            }

            this.setText(null);
            this.setGraphic(this.cell);
        }

}
