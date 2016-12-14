package views;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import models.Favorite;
import models.Video;

import java.io.IOException;


public class FavoriteListViewCell extends JFXListCell<Favorite> {

    @FXML
    private GridPane favorite;
    @FXML
    private ImageView thumbnail;
    @FXML
    private Label title;

    private FXMLLoader loader;

    public void updateItem(Favorite value, boolean empty) {
        super.updateItem(value, empty);

        if(empty || value == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (this.loader == null) {
                this.loader = new FXMLLoader(getClass().getResource("/fxml/favorite.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(value != null) {
                this.title.setText(value.getFavorite().getTitle());
                this.thumbnail.setImage(new Image(value.getFavorite().getThumbnail()));
            }

            this.setText(null);
            this.setGraphic(this.favorite);
        }
    }
}
