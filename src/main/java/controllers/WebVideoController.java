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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
    private BorderPane root;

    public WebVideoController(String videoID) {
        ID = videoID;
        this.FS = new Button();
        this.Return = new Button();
    }
    
    @FXML
    private void initialize() {
        
        
        
       
        
        this.FS.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appController.goFullScreen();
            }
        });
        
        this.Return.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                appController.showHome();
            }
        });
    }

}
