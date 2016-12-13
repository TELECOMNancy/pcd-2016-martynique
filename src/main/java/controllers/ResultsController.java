package controllers;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXListView;
//import javafx.scene.control.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import models.Video;
import views.SearchResultListViewCell;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Controller for results of request.
 */
@SuppressWarnings("restriction")
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
        this.results.setItems(this.searchResultsObservableList);
        this.results.setCellFactory(param -> new SearchResultListViewCell());        
        
     // Handle JFXListView selection changes.
        this.results.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("JFXListView Selection Changed (selected: " + newValue.getCode() + ")");
            this.appController.playWebVideo(newValue.getCode());
        });
    }

}
