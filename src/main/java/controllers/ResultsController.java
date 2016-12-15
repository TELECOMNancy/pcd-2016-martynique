package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import models.Video;
import views.VideoListViewCell;

import java.util.List;

/**
 * Controller for results of request.
 */
public class ResultsController extends Controller {

    @FXML
    JFXListView<Video> results;

    private ObservableList<Video> searchResultsObservableList;

    public ResultsController(List<Video> results) {
        for(Video v: results) {
            if(this.app.getUser().hasFavorite(v))
                v.setFavorite(true);
        }
        this.results = new JFXListView<Video>();
        this.searchResultsObservableList = FXCollections.observableArrayList();
        this.searchResultsObservableList.setAll(results);
    }
    
    public void updateResults(List<Video> results) {
        for(Video v: results) {
            if(this.app.getUser().hasFavorite(v))
                v.setFavorite(true);
        }
        this.searchResultsObservableList.setAll(results);
    }
    
    @FXML
    private void initialize() {
        this.results.setItems(this.searchResultsObservableList);
        this.results.setCellFactory(param -> new VideoListViewCell(app.getAppController()));
    }

    public Node getScene() {
        return results;
    }
}
