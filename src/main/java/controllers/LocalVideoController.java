package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import views.LocalPlayer;

/**
 * Controller for playing Local videos.
 */
public class LocalVideoController extends Controller implements VideoController{
    
    private LocalPlayer lp; 
    
    @FXML private Button Return;
    @FXML private Button FS;
    @FXML private Button Mute;
    @FXML private Button Play;
    @FXML private Button ReDL;
    @FXML private Slider Volume;
    @FXML private Slider ProgressBar;
    @FXML private Label Title;
    @FXML private BorderPane Video;
    @FXML private BorderPane Overlay;

    public LocalVideoController(String path) {
        this.FS = new Button();
        this.Return = new Button();
        this.Mute = new Button();
        this.Play = new Button();
        this.ReDL = new Button();
        this.Volume = new Slider();
        this.ProgressBar = new Slider();
        
        lp = new LocalPlayer(path);
        
        //this.Video.setCenter(lp);
    }
    
    @FXML
    private void initialize() {
        
        this.FS.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goFullScreen();
            }
        });
        
        this.Return.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Return");
                appController.showHome();
            }
        });
    }
    
    
    
    public void hideOverlay() {
        this.Overlay.setVisible(false);
        //this.Overlay.setManaged(false);
    }
    
    public void showOverlay() {
        this.Overlay.setVisible(true);
        //this.Overlay.setManaged(true);
    }
    
    @Override
    public void goFullScreen() {
        appController.goFullScreen(LocalVideoController.this);
    }
    
    @Override
    public void quitFullScreen() {}

}
