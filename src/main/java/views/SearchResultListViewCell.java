package views;

import com.jfoenix.controls.JFXListCell;
import controllers.AppController;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Favorite;
import models.Video;

import java.io.IOException;

/**
 * Created by mcdostone on 12/12/16.
 */
public class SearchResultListViewCell extends JFXListCell<Video> {

    @FXML
    private GridPane searchResult;
    @FXML
    private ImageView thumbnail;
    @FXML
    private Label title;
    @FXML
    private Label isFavorite;

    private FXMLLoader loader;
    private AppController ctrl;
    private Video v;

    public SearchResultListViewCell(AppController ctrl) {
        this.ctrl = ctrl;

    }

    public void updateItem(Video value, boolean empty) {
        super.updateItem(value, empty);

        if(empty || value == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (this.loader == null) {
                this.loader = new FXMLLoader(getClass().getResource("/fxml/searchResult.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(value != null) {
                this.title.setText(value.getTitle());
                this.thumbnail.setImage(new Image(value.getThumbnail()));
            }
            if(ctrl.getUser().hasFavorite(value))
                this.isFavorite.getStyleClass().add("favorite");


            this.setText(null);
            this.setGraphic(this.searchResult);

            this.thumbnail.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ctrl.playWebVideo(value.getCode());
                }
            });

            this.title.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ctrl.playWebVideo(value.getCode());
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
    }

}
