import java.io.File;
import java.io.IOException;

import javafx.scene.layout.Pane;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;

import javafx.fxml.FXMLLoader;
 
@SuppressWarnings("restriction")
public class test extends Pane implements VideoPlayer {  
    private MediaView player;
    
    public test(int height, int width, String source) {
        super();
        this.setHeight(height);
        this.setWidth(width);
        
        source = "E:/workspace/pcd-2016-martynique/src/main/resources/fxml/localPlayer.fxml";
        
        /*
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
        mediaPlayer.setAutoPlay(true);

        // Create the view and add it to the Scene.
        MediaView player = new MediaView(mediaPlayer);
        
        player.setFitHeight(this.getHeight());
        player.setFitWidth(this.getWidth());
        
        this.getChildren().addAll(player);
        */
    }

    public MediaView getPlayer() {
        return this.player;
    }
    
    public String getMediaName() {
        // a changer pour recuperer depuis les meta données
        return this.player.getMediaPlayer().getMedia().getSource();
    }

    public String getSource() {
        return this.player.getMediaPlayer().getMedia().getSource();
    }
}