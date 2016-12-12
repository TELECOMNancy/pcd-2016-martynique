import app.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneManager sm = new SceneManager(primaryStage);
        sm.startHomepage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
