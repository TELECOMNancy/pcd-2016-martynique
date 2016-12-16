package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import javafx.beans.value.ChangeListener;
import views.LocalPlayer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;

import com.jfoenix.controls.JFXSlider;

import javafx.util.Duration;

import java.io.File;

/**
 * Controller for playing Local videos.
 */
public class LocalVideoController extends Controller implements VideoController{
    
    private LocalPlayer lp;
    private double savedVolume;
    
    @FXML private Button returnButton;
    @FXML private Button fsButton;
    @FXML private Button smallButton;
    @FXML private Button muteButton;
    @FXML private Button unmuteButton;
    @FXML private Button playButton;
    @FXML private Button pauseButton;
    @FXML private JFXSlider volume;
    @FXML private SliderBar progressBar;
    @FXML private Label Title;
    @FXML private BorderPane Video;
    @FXML private BorderPane Overlay;
    @FXML private BorderPane bottomLayout;
    @FXML private StackPane stackPane;

    public LocalVideoController(File path) {
        this.savedVolume = -1;
        lp = new LocalPlayer(path);
        
        this.fsButton = new Button();
        this.smallButton = new Button();
        this.returnButton = new Button();
        this.muteButton = new Button();
        this.unmuteButton = new Button();
        this.playButton = new Button();
        this.pauseButton = new Button();
        this.volume = new JFXSlider();
        this.progressBar = new SliderBar();
    }
    
    @FXML
    private void initialize() {
        this.Title.setText(lp.getMediaName());;
        pause();
        unmute();
        smallButton.setVisible(false);
        smallButton.setManaged(false);
        this.Video.setCenter(lp);
        this.bottomLayout.setTop(this.progressBar);
        
        this.volume.setValue(100);
        
        this.Video.prefWidthProperty().bind(this.stackPane.widthProperty());
        this.Video.prefHeightProperty().bind(this.stackPane.heightProperty());
        
        this.lp.prefWidthProperty().bind(this.Video.widthProperty());
        this.lp.prefHeightProperty().bind(this.Video.heightProperty());
        
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(5000),
                ae-> hideOverlay()));
        
        this.Overlay.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showOverlay();
                timeline.stop();
                timeline.play();
            }
        });
        
        this.Video.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showOverlay();
            }
        });
        
        
        this.lp.getPlayer().currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                progressBar.sliderValueProperty().setValue(lp.getProgress() * 100.0);
            }
        });
        
        this.progressBar.sliderValueProperty().addListener((ov) -> {
            if (progressBar.isTheValueChanging()) {
                if (null != lp.getPlayer()) {
                    lp.setProgress(progressBar.sliderValueProperty().getValue() / 100.0);
                } else {
                    System.out.println("else");
                    progressBar.sliderValueProperty().setValue(0);
                }
            }
        });
        
        this.volume.valueProperty().addListener((ov) -> {
            if (volume.isPressed()) {
                if (null != lp.getPlayer()) {
                    lp.setVolume(volume.getValue());
                    if (volume.getValue() == 0)
                        mute();
                    else
                        unmute();
                }
                
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
                showOverlay();
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
        savedVolume = volume.getValue();
        this.volume.setValue(0);
        lp.mute();
    }
    
    public void unmute() {
        
            this.unmuteButton.setVisible(false);
            this.unmuteButton.setManaged(false);
            this.muteButton.setVisible(true);
            this.muteButton.setManaged(true);
        
        if (savedVolume != -1) {
            this.volume.setValue(savedVolume);
            savedVolume = -1;
            lp.unmute();
        }
    }
    
    
    public void hideOverlay() {
        app.getAppController().hideCursor();
        this.Overlay.setVisible(false);
    }
    
    public void showOverlay() {
        app.getAppController().showCursor();
        this.Overlay.setVisible(true);
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

        private JFXSlider slider = new JFXSlider();

        public SliderBar() {
            slider.setValue(0);
            getChildren().addAll(slider);
        }

        public DoubleProperty sliderValueProperty() {
            return slider.valueProperty();
        }

        public boolean isTheValueChanging() {
            return slider.isPressed();
        }
    }
}
