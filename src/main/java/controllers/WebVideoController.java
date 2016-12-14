package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import models.User;

import views.WebPlayer;

/**
 * Controller for playing web videos.
 */
public class WebVideoController extends Controller implements VideoController{
    
    private String ID;
    
    @FXML private Button Return;
    @FXML private Button FS;
    @FXML private Button Download;
    @FXML private BorderPane Player;
    @FXML private BorderPane TopBar;

    public WebVideoController(String videoID) {
        super();
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
                TopBar.setVisible(false);
                TopBar.setManaged(false);
                System.out.println("FS");
                app.getAppController().goFullScreen(WebVideoController.this);
            }
        });
        
        this.Download.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hello");
                app.getAppController().download(ID);
            }
        });
        
        this.Return.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Return");
                app.getAppController().showHome();
            }
        });
    }
    
    @Override
    public void goFullScreen() {
        TopBar.setVisible(false);
        TopBar.setManaged(false);
        this.app.getAppController().goFullScreen(WebVideoController.this);
    }
    
    @Override
    public void quitFullScreen() {
        TopBar.setVisible(true);
        TopBar.setManaged(true);
    }

}
