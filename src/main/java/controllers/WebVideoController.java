package controllers;

import com.google.api.services.youtube.model.SearchResult;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import views.SearchResultListViewCell;

import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;
import java.util.List;

import views.WebPlayer;

/**
 * Controller for playing web videos.
 */
@SuppressWarnings("restriction")
public class WebVideoController extends Controller {
    
    private String ID;
    
    @FXML
    private Button Return;
    
    @FXML
    private Button FS;
    
    @FXML
    private Button Download;
    
    @FXML
    private BorderPane Player;

    public WebVideoController(String videoID) {
        ID = videoID;
        this.FS = new Button();
        this.Return = new Button();
        this.Download = new Button();
    }
    
    @FXML
    private void initialize() {
        
        this.FS.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Player.getTop().setVisible(false);
                System.out.println("FS");
                appController.goFullScreen();
            }
        });
        
        this.Download.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hello");
                appController.download(ID);
            }
        });
        
        this.Return.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Return");
                appController.stopWebVideo();
                appController.showHome();
            }
        });
    }

}
