package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import models.Video;

/**
 * Controller for playing web videos.
 */
public class WebVideoController extends Controller implements VideoController{
    
    private String ID;
    
    @FXML private Button returnButton;
    @FXML private Button fsButton;
    @FXML private Button dlButton;
    @FXML private BorderPane Player;
    @FXML private BorderPane TopBar;

    private Video v;

    public WebVideoController(Video v) {
        super();
        this.v = v;
        this.fsButton = new Button();
        this.returnButton = new Button();
        this.dlButton = new Button();
    }


    @FXML
    private void initialize() {
        
        this.fsButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TopBar.setVisible(false);
                TopBar.setManaged(false);
                System.out.println("FS");
                app.getAppController().goFullScreen(WebVideoController.this);
            }
        });
        
        this.dlButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                app.getAppController().download(v);
            }
        });
        
        this.returnButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                app.getAppController().stopWebVideo();
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
