import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.concurrent.Worker.State;
 
@SuppressWarnings("restriction")
public class WebPlayerV2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Martynique");
        
        StackPane root = new StackPane();
        WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
		
        /* CHANGE WINDOW'S NAME WITH YT VIDEO'S NAME
		webEngine.getLoadWorker().stateProperty().addListener(
		    new ChangeListener<State>() {
		        public void changed(ObservableValue ov, State oldState, State newState) {
		            if (newState == State.SUCCEEDED)
		                primaryStage.setTitle(webEngine.getTitle());
		        }
		    }
		);
		*/
        
        //webEngine.load("http://www.google.com");
        webEngine.load("https://www.youtube.com/embed/FIRT7lf8byw");
        
        root.getChildren().add(webView);
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
}