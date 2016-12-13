package views;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import javafx.scene.layout.BorderPane;
 
@SuppressWarnings("restriction")
public class WebPlayer extends BorderPane implements VideoPlayer{
    private WebView player;
    
    private String videoID;
    
    public WebPlayer() {
        super();
        
        this.player = new WebView();
        
        //this.player.set
        
        this.setCenter(this.player);
        
    }
    
    public void play(String videoID) {
        this.videoID = videoID;
        player.getEngine().load( "https://www.youtube.com/embed/"+videoID);
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