package controllers;


import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Video;
import views.LocalVideoListViewCell;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class LocalListController extends Controller implements Observer {

    @FXML
    JFXListView<Video> results;

    private ObservableList<Video> localVideosObservableList;
    private List<Video> favorites;

    public LocalListController() {
        this.app.getUser().addObserver(this);
        this.results = new JFXListView<Video>();
        this.localVideosObservableList = FXCollections.observableArrayList(app.getUser().getFavorites());
    }

    @FXML
    public void initialize() {
        this.results.setItems(this.localVideosObservableList);
        this.results.setCellFactory(param -> new LocalVideoListViewCell(app.getAppController()));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("add-favorite") || arg.equals("remove-favorite")) {
            this.localVideosObservableList.clear();
            this.localVideosObservableList.addAll(app.getUser().getFavorites());
        }
    }

}
