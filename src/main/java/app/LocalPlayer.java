package app;
import java.io.File;
//import java.time.Duration;

import javafx.scene.layout.BorderPane;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;

import javafx.util.Duration;
 
public class LocalPlayer extends BorderPane implements VideoPlayer {  
    private MediaView player;
    
    public LocalPlayer(String source) {
        super();

        //String source = getParameters().getRaw().get(0);
        //String source = "E:/workspace/pcd-2016-martynique/savedVideos/test.mp4";
        //String source = "E:/workspace/pcd-2016-martynique/savedVideos/Westworld.mp4";
        
        String url = "";
        try{
            File video = new File(source);
            url = video.toURI().toURL().toString();
            System.out.println("URL: "+url);
        }catch(Exception e){
            System.err.println(e.toString());
        }
                
        Media media = new Media(url);
               
        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(false);

        // Create the view and add it to the Scene.
        player = new MediaView(mediaPlayer);
        
        player.fitHeightProperty().bind(this.heightProperty());
        player.fitWidthProperty().bind(this.widthProperty());
        
        this.setCenter(player);
    }
    
    public void mute() {
        player.getMediaPlayer().setMute(true);
    }
    
    public void unmute() {
        player.getMediaPlayer().setMute(false);
    }
    
    public void play(){
        player.getMediaPlayer().play();
    }
    
    public void pause(){
        player.getMediaPlayer().pause();
    }

    public void setVolume(double vol) {
        player.getMediaPlayer().setVolume(vol/100);
    }
    
    public void setProgress(double progressPct) {
        Duration seekTime = new Duration(player.getMediaPlayer().getStopTime().toMillis()*progressPct);
        player.getMediaPlayer().seek(seekTime);
    }
    
    public double getProgress() {
        return player.getMediaPlayer().getCurrentTime().toSeconds()/player.getMediaPlayer().getStopTime().toSeconds();
    }

    public String getPlayerType() {
        return "LocalPlayer";
    }
    
    public MediaPlayer getPlayer() {
        return this.player.getMediaPlayer();
    }
    
    public String getMediaName() {
        // a changer pour recuperer depuis les meta données
        return this.player.getMediaPlayer().getMedia().getSource();
    }

    public String getSource() {
        return this.player.getMediaPlayer().getMedia().getSource();
    }
}