package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import models.Video;
import views.VideoListViewCell;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class FavoritesController extends TabController implements Observer {

    private JFXListView<Video> list;
    private ObservableList<Video> favoritesObservableList;
    private List<Video> favorites;

    public FavoritesController() {
        this.app.getUser().addObserver(this);
        this.list = new JFXListView<Video>();
        this.favoritesObservableList = FXCollections.observableArrayList(app.getUser().getFavorites());
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.list.setItems(this.favoritesObservableList);
        this.list.setCellFactory(param -> new VideoListViewCell(app.getAppController()));
        this.tab.setCenter(this.list);
    }

    @Override
    public String getTabName() {
        return "Favoris";
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("add-favorite") || arg.equals("remove-favorite")) {
            this.favoritesObservableList.clear();
            this.favoritesObservableList.addAll(app.getUser().getFavorites());
        }
    }

}
