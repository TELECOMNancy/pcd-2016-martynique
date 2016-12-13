package controllers;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Video;
import views.SearchResultListViewCell;

import java.util.List;

/**
 * Controller for results of request.
 */
public class ResultsController extends Controller {

    @FXML
    JFXListView<Video> results;

    private ObservableList<Video> searchResultsObservableList;

    public ResultsController(List<Video> results) {
        this.results = new JFXListView<Video>();
        this.searchResultsObservableList = FXCollections.observableArrayList();
        if(searchResultsObservableList == null)
        	System.out.println("YYOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        this.searchResultsObservableList.addAll(results);
    }

    @FXML
    private void initialize() {
        this.results.getItems().addAll(this.searchResultsObservableList);
        this.results.setCellFactory(param -> new SearchResultListViewCell());
    }

}
