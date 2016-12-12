import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXMLLoader;
 
@SuppressWarnings("restriction")
public class WebPlayerV2 extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
    public void start(final Stage primaryStage) {
		this.primaryStage = primaryStage;
        primaryStage.setTitle("Martynique");
        
        initRootlayout();
    }
	
	public void initRootlayout(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(WebPlayerV2.class.getResource("src/main/resources/sample.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			StackPane root = new StackPane();
	        WebView webView = new WebView();
	        final WebEngine webEngine = webView.getEngine();
	        webEngine.load("https://www.youtube.com/embed/FIRT7lf8byw");
	        
	        rootLayout.setCenter(webView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}