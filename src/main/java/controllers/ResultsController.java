package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        System.out.println(app.getUser());
        System.out.println(app.getAppController());
        this.results = new JFXListView<Video>();
        this.searchResultsObservableList = FXCollections.observableArrayList();
        this.searchResultsObservableList.addAll(results);
    }
    
    @FXML
    private void initialize() {
        this.results.setItems(this.searchResultsObservableList);
        this.results.setCellFactory(param -> new SearchResultListViewCell(app.getAppController()));

        /*this.results.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("JFXListView Selection Changed (selected: " + newValue.getCode() + ")");
            this.appController.playWebVideo(newValue.getCode());
        });*/
    }

    public Node getScene() {
        return results;
    }
}
