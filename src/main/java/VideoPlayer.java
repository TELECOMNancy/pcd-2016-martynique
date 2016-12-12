import javafx.scene.layout.Pane;

public interface VideoPlayer {

    public String getMediaName();
    
    public String getSource();
    
    public javafx.scene.media.MediaView getPlayer();
    
}
