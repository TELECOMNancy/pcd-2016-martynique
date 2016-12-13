import app.SceneManager;
import db.FavoriteDB;
import db.VideoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Favorite;
import models.Video;

@SuppressWarnings("restriction")
public class Main extends Application {

    private void initDB() {
        VideoDB.createTable();
        FavoriteDB.createTable();
        Video v = new Video("y", "google.fr", "fjkd555");

        new VideoDB(v).create();
        new FavoriteDB(new Favorite(v)).create();
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
