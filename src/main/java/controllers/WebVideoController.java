package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

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

    public WebVideoController(String videoID) {
        ID = videoID;
        this.fsButton = new Button();
        this.returnButton = new Button();
        this.dlButton = new Button();
    }
    
    @FXML
    private void initialize() {
        
        this.fsButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goFullScreen();
            }
        });
        
        this.dlButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hello");
                appController.download(ID);
            }
        });
        
        this.returnButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("returnButton");
                appController.stopWebVideo();
                appController.showHome();
            }
        });
    }
    
    @Override
    public void goFullScreen() {
        TopBar.setVisible(false);
        TopBar.setManaged(false);
        appController.goFullScreen(WebVideoController.this);
    }
    
    @Override
    public void quitFullScreen() {
        TopBar.setVisible(true);
        TopBar.setManaged(true);
    }

}
