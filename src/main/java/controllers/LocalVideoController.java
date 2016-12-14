package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;

import javafx.util.Duration;




import views.LocalPlayer;

/**
 * Controller for playing Local videos.
 */
public class LocalVideoController extends Controller implements VideoController{
    
    private LocalPlayer lp; 
    
    @FXML private Button returnButton;
    @FXML private Button fsButton;
    @FXML private Button smallButton;
    @FXML private Button muteButton;
    @FXML private Button unmuteButton;
    @FXML private Button playButton;
    @FXML private Button pauseButton;
    @FXML private Slider volume;
    @FXML private SliderBar progressBar;
    @FXML private Label Title;
    @FXML private BorderPane Video;
    @FXML private BorderPane Overlay;
    @FXML private BorderPane bottomLayout;

    public LocalVideoController(String path) {
        this.fsButton = new Button();
        this.smallButton = new Button();
        this.returnButton = new Button();
        this.muteButton = new Button();
        this.unmuteButton = new Button();
        this.playButton = new Button();
        this.pauseButton = new Button();
        //this.volume = new Slider();
        this.progressBar = new SliderBar();
        
        lp = new LocalPlayer(path);
    }
    
    @FXML
    private void initialize() {
        this.Title = new Label(lp.getMediaName());
        pause();
        unmute();
        smallButton.setVisible(false);
        smallButton.setManaged(false);
        this.Video.setCenter(lp);
        this.bottomLayout.setTop(this.progressBar);
        
        lp.getPlayer().currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                progressBar
                        .sliderValueProperty()
                        .setValue(lp.getProgress() * 100.0);
            }
        });
        
        progressBar.sliderValueProperty().addListener((ov) -> {
            if (progressBar.isTheValueChanging()) {
                if (null != lp.getPlayer())
                    // multiply duration by percentage calculated by
                    // slider position
                    lp.setProgress(progressBar
                            .sliderValueProperty().getValue() / 100.0);
                else
                    progressBar.sliderValueProperty().setValue(0);
            }
        });
        
        this.fsButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goFullScreen();
            }
        });
        
        this.smallButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                quitFullScreen();
            }
        });
        
        this.returnButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pause();
                app.getAppController().showHome();
            }
        });
        
        this.playButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                play();
            }
        });
        
        this.pauseButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pause();
            }
        });
        
        this.muteButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mute();
            }
        });
        
        this.unmuteButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unmute();
            }
        });
    }
    
    public void play() {
        this.playButton.setVisible(false);
        this.playButton.setManaged(false);
        this.pauseButton.setVisible(true);
        this.pauseButton.setManaged(true);
        lp.play();
    }
    
    public void pause() {
        this.pauseButton.setVisible(false);
        this.pauseButton.setManaged(false);
        this.playButton.setVisible(true);
        this.playButton.setManaged(true);
        lp.pause();
    }
    
    public void mute() {
        this.muteButton.setVisible(false);
        this.muteButton.setManaged(false);
        this.unmuteButton.setVisible(true);
        this.unmuteButton.setManaged(true);
        lp.mute();
    }
    
    public void unmute() {
        this.unmuteButton.setVisible(false);
        this.unmuteButton.setManaged(false);
        this.muteButton.setVisible(true);
        this.muteButton.setManaged(true);
        lp.unmute();
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
        this.fsButton.setVisible(false);
        this.fsButton.setManaged(false);
        this.smallButton.setVisible(true);
        this.smallButton.setManaged(true);
        app.getAppController().goFullScreen(LocalVideoController.this);
    }
    
    @Override
    public void quitFullScreen() {
        this.smallButton.setVisible(false);
        this.smallButton.setManaged(false);
        this.fsButton.setVisible(true);
        this.fsButton.setManaged(true);
        app.getAppController().quitFullScreen();
    }
    
    private class SliderBar extends StackPane {

        private Slider slider = new Slider();

        private ProgressBar progressBar = new ProgressBar();

        public SliderBar() {
            getChildren().addAll(progressBar, slider);
            bindValues();
        }
        private void bindValues(){
            progressBar.prefWidthProperty().bind(slider.widthProperty());
            progressBar.progressProperty().bind(slider.valueProperty().divide(100));
        }

        public DoubleProperty sliderValueProperty() {
            return slider.valueProperty();
        }

        public boolean isTheValueChanging() {
            return slider.isValueChanging();
        }
    }
}
