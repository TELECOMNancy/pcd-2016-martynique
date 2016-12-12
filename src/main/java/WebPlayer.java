import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
 
@SuppressWarnings("restriction")
public class WebPlayer extends Pane implements VideoPlayer{
    private WebView player;
    
    public WebPlayer(int height, int width, String source) {
        super();
        this.setHeight(height);
        this.setWidth(width);
        
        this.player = new WebView();
        WebEngine webEngine = this.player.getEngine();
        
        /*
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SUCCEEDED) {
                            primaryStage.setTitle(webEngine.getTitle());
                        }
                    }
                });
        */        
        
        webEngine.load(source);
        
        this.getChildren().add(this.player);
    }
    
    public String getMediaName() {
        return this.player.getEngine().getTitle();
    }
    
    public String getSource() {
        return this.player.getEngine().getLocation();
    }

    public MediaView getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }
}