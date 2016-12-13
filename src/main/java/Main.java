import app.SceneManager;
import db.FavoriteDB;
import db.VideoDB;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Favorite;
import models.Video;

public class Main extends Application {

    private void initDB() {
        VideoDB.createTable();
        FavoriteDB.createTable();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.initDB();

        FXMLLoader loader = SceneManager.getLoader("homepage.fxml");
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
