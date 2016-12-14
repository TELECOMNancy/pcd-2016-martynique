package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

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
    
    @FXML
    private BorderPane TopBar;

    public WebVideoController(String videoID) {
        ID = videoID;
        this.FS = new Button();
        this.Return = new Button();
        this.Download = new Button();
    }
    
    public void quitFullScreen() {
        TopBar.setVisible(true);
        TopBar.setManaged(true);
    }
    
    @FXML
    private void initialize() {
        
        this.FS.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TopBar.setVisible(false);
                TopBar.setManaged(false);
                System.out.println("FS");
                appController.goFullScreen(WebVideoController.this);
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
