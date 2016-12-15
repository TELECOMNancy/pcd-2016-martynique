package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import models.Video;
import views.VideoListViewCell;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Controller for results of request.
 */
public class SetListController extends Controller {

    @FXML
    JFXListView<Video> list;

    private ObservableList<Video> searchResultsObservableList;

    public SetListController(List<Video> results) {
        //this.app.getUser().addObserver(this);
        for(Video v: results) {
            if(this.app.getUser().hasFavorite(v))
                v.setFavorite(true);
        }
        this.list = new JFXListView<Video>();
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
        this.list.setItems(this.searchResultsObservableList);
        this.list.setCellFactory(param -> new VideoListViewCell(app.getAppController()));
    }

    public Node getScene() {
        return list;
    }

}
