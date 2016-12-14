package controllers;


import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Favorite;
import models.Video;
import views.FavoriteListViewCell;
import views.SearchResultListViewCell;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FavoritesController extends Controller {

    @FXML
    JFXListView<Favorite> results;

    private final ObservableList<Favorite> favoritesObservableList;
    private List<Favorite> favorites;

    public FavoritesController() {
        this.results = new JFXListView<Favorite>();
        this.favoritesObservableList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        this.favoritesObservableList.addAll(app.getUser().getFavorites());
        this.results.setItems(this.favoritesObservableList);
        this.results.setCellFactory(param -> new FavoriteListViewCell());
    }
}
