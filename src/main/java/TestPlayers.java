import app.LocalPlayer;
import app.VideoPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class TestPlayers extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Martynique");
        StackPane root = new StackPane();
        
        //String source = "E:/workspace/pcd-2016-martynique/savedVideos/Westworld.mp4";
        
        VideoPlayer player = new LocalPlayer(new File("E:/workspace/pcd-2016-martynique/savedVideos/Westworld.mp4"));
        //VideoPlayer player = new WebPlayer();
        //((WebPlayer) player).play("SNE2oCZH_4k");
        //((WebPlayer) player).play("Nj6PFaDmp6c");
        
        
        //VideoPlayer player = new test(600, 500, "https://www.youtube.com/embed/SnxNJbWCcng");
        
        root.getChildren().add((Pane) player);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}