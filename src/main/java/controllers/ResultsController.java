package controllers;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import views.SearchResultListViewCell;

import java.util.List;

/**
 * Controller for results of request.
 */
public class ResultsController extends Controller {

    @FXML
    JFXListView<SearchResult> results;

    private ObservableList<SearchResult> searchResultsObservableList;

    public ResultsController(List<SearchResult> results) {
        this.results = new JFXListView<SearchResult>();
        this.searchResultsObservableList = FXCollections.observableArrayList();
        this.searchResultsObservableList.addAll(results);
    }

    @FXML
    private void initialize() {
        this.results.getItems().addAll(this.searchResultsObservableList);
        this.results.setCellFactory(param -> new SearchResultListViewCell());
    }

}
