package controllers;


import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Video;
import views.FavoriteListViewCell;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FavoritesController extends Controller implements Observer {

    @FXML
    JFXListView<Video> results;

    private final ObservableList<Video> favoritesObservableList;
    private List<Video> favorites;

    public FavoritesController() {
        this.app.getUser().addObserver(this);
        this.results = new JFXListView<Video>();
        this.favoritesObservableList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        this.favoritesObservableList.addAll(app.getUser().getFavorites());
        this.results.setItems(this.favoritesObservableList);
        this.results.setCellFactory(param -> new FavoriteListViewCell(app.getAppController()));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("add-favorite")) {
            this.favoritesObservableList.removeAll();
            this.favoritesObservableList.addAll(app.getUser().getFavorites());
            this.results.setItems(this.favoritesObservableList);
            this.results.setCellFactory(param -> new FavoriteListViewCell(app.getAppController()));
        }
    }
}
