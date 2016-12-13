import app.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main_Form_Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = SceneManager.getLoader("form.fxml");
        Parent root = SceneManager.getComponent(loader);
        primaryStage.setTitle("Youtube app");
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(450);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
