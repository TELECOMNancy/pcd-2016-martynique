package views;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by mcdostone on 12/12/16.
 */
public class SearchResultListViewCell extends JFXListCell<SearchResult> {

    @FXML
    private GridPane searchResult;
    @FXML
    private ImageView thumbnail;
    @FXML
    private Label title;

    private FXMLLoader loader;

    public void updateItem(SearchResult value, boolean empty) {
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
            if(value != null && value.getSnippet() != null) {

                this.title.setText(value.getSnippet().getTitle());
                this.thumbnail.setImage(new Image(value.getSnippet().getThumbnails().getDefault().getUrl()));
            }

            this.setText(null);
            this.setGraphic(this.searchResult);
        }
    }
}
