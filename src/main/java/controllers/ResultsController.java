package controllers;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXListView;
//import javafx.scene.control.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import views.SearchResultListViewCell;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Controller for results of request.
 */
@SuppressWarnings("restriction")
public class ResultsController extends Controller {

    @FXML
    private JFXListView<SearchResult> results;

    private ObservableList<SearchResult> searchResultsObservableList;

    public ResultsController(List<SearchResult> results) {
        this.results = new JFXListView<SearchResult>();
        this.searchResultsObservableList = FXCollections.observableArrayList();
        this.searchResultsObservableList.addAll(results);
    }
    
    @FXML
    private void initialize() {
        this.results.setItems(this.searchResultsObservableList);
        this.results.setCellFactory(param -> new SearchResultListViewCell());        
        
     // Handle JFXListView selection changes.
        this.results.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("JFXListView Selection Changed (selected: " + newValue.getId().getVideoId().toString() + ")");
            this.appController.playWebVideo(newValue.getId().getVideoId().toString());
        });
    }

}
