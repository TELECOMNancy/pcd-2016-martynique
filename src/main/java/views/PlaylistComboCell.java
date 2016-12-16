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

/**
 * Main listCell of the app (for favorites and searchResults
 */
public class PlaylistComboCell extends JFXListCell<Playlist> {


    @FXML private Label playlistCombo;


    public void updateItem(Playlist value, boolean empty) {
        super.updateItem(value, empty);
        if(value != null && !empty) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playlistComboBox.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            playlistCombo.setText(value.getName());
        }
        this.setText(null);
        this.setGraphic(this.playlistCombo);
    }

}

