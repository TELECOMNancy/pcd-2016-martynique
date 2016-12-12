package controllers;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Controller for results of request.
 */
public class ResultsController {

    @FXML
    JFXListView<String> results;

    List<SearchResult> ff;

    public ResultsController(List<SearchResult> results) {
        this.results = new JFXListView<String>();
        this.ff = results;
    }

    @FXML
    private void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for(SearchResult r: ff)
            items.add(r.getSnippet().getTitle());

        this.results.setItems(items);
    }

}
