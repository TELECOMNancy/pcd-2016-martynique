package controllers;


import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Video;
import views.VideoListViewCell;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FavoritesController extends Controller implements Observer {

    @FXML
    JFXListView<Video> results;

    private ObservableList<Video> favoritesObservableList;
    private List<Video> favorites;

    public FavoritesController() {
        this.app.getUser().addObserver(this);
        this.results = new JFXListView<Video>();
        this.favoritesObservableList = FXCollections.observableArrayList(app.getUser().getFavorites());
    }

    @FXML
    public void initialize() {
        this.results.setItems(this.favoritesObservableList);
        this.results.setCellFactory(param -> new VideoListViewCell(app.getAppController()));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("add-favorite") || arg.equals("remove-favorite")) {
            this.favoritesObservableList.clear();
            this.favoritesObservableList.addAll(app.getUser().getFavorites());
        }
    }

}
