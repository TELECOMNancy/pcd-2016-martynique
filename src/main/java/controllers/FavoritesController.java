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

public class FavoritesController extends Controller {

    @FXML
    JFXListView<Favorite> results;

    private final ObservableList<Favorite> favoritesObservableList;
    private List<Favorite> favorites;

    public FavoritesController(List<Favorite> f) {
        this.favorites = f;
        this.results = new JFXListView<>();
        this.favoritesObservableList = FXCollections.observableArrayList();
        this.favoritesObservableList.addAll(this.favorites);
    }

    @FXML
    private void initialize() {
        this.results.setItems(this.favoritesObservableList);
        this.results.setCellFactory(param -> new FavoriteListViewCell());
    }


}
