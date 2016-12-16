package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import utils.LocalFiles;
import views.DownloadedVideoCell;

import java.io.File;
import java.util.Observable;
import java.util.Observer;


public class DownloadsController extends TabController implements Observer {

    private JFXListView<File> list;
    private ObservableList<File> downloadsObservableList;


    public DownloadsController() {
        this.app.getUser().addObserver(this);
        this.list = new JFXListView<File>();
        //this.downloadsObservableList = FXCollections.observableArrayList(app.getUser().getDownloads());
        this.downloadsObservableList = FXCollections.observableArrayList(LocalFiles.getDownloads());
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.list.setItems(this.downloadsObservableList);
        this.list.setCellFactory(param -> new DownloadedVideoCell(app.getAppController()));
        this.tab.setCenter(this.list);
    }

    @Override
    public String getTabName() {
        return "Téléchargements";
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("add-download")) {
            this.downloadsObservableList.clear();
            //this.downloadsObservableList.addAll(LocalFiles.getDownloads());
        }
    }
}
