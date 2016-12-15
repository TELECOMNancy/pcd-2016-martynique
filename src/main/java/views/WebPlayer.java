package views;
import app.VideoPlayer;
import javafx.scene.web.WebView;

import javafx.scene.layout.BorderPane;
import models.Video;

public class WebPlayer extends BorderPane implements VideoPlayer {
    private WebView player;
    
    private Video video;
    
    public WebPlayer() {
        super();
        this.player = new WebView();
        this.setCenter(this.player);
    }
    
    public void play(Video video) {
        this.video = video;
        player.getEngine().load( "https://www.youtube.com/embed/"+ video.getID());
    }
    
    public void stop() {
        this.player.getEngine().load(null);
    }
    
    public String getMediaName() {
        return this.player.getEngine().getTitle();
    }
    
    public String getSource() {
        return this.player.getEngine().getLocation();
    }

    public String getPlayerType() {
        return "WebPlayer";
    }
}