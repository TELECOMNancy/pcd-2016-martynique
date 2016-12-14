package controllers;


import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Video;
import views.FavoriteListViewCell;

import java.util.List;

public class FavoritesController extends Controller {

    @FXML
    JFXListView<Video> results;

    private final ObservableList<Video> favoritesObservableList;
    private List<Video> favorites;

    public FavoritesController() {
        this.results = new JFXListView<Video>();
        this.favoritesObservableList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        this.favoritesObservableList.addAll(app.getUser().getFavorites());
        this.results.setItems(this.favoritesObservableList);
        this.results.setCellFactory(param -> new FavoriteListViewCell());
    }
}
