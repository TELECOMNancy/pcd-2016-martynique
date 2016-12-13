import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.VideoPlayer;
import views.WebPlayer;
 
@SuppressWarnings("restriction")
public class TestPlayers extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Martynique");
        StackPane root = new StackPane();
        
        //String source = "E:/workspace/pcd-2016-martynique/savedVideos/Westworld.mp4";
        
        //VideoPlayer player = new LocalPlayer(600, 500, "E:/workspace/pcd-2016-martynique/savedVideos/Westworld.mp4");
        VideoPlayer player = new WebPlayer();
        ((WebPlayer) player).play("SNE2oCZH_4k");
        
        //VideoPlayer player = new test(600, 500, "https://www.youtube.com/embed/SnxNJbWCcng");
        
        root.getChildren().add((Pane) player);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}