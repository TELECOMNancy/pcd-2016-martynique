import app.App;
import app.SceneManager;
import db.PlaylistDB;
import db.PlaylistLinkDB;
import db.SuggestionDB;
import db.VideoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Playlist;
import models.Video;

import java.util.List;

import app.Configuration;


public class Main extends Application {

    private final App app = App.getInstance();

    private void initDB() {
        VideoDB.createTable();
        SuggestionDB.createTable();
        PlaylistDB.createTable();
        PlaylistLinkDB.createTable();
    }

    private void initUser() {
        List<Video> fav = VideoDB.getFavorites();
        for(Video f: fav)
            this.app.getUser().addFavorite(f);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Configuration.getInstance();
        Configuration.getInstance().resetSettings();
        this.initDB();
        this.initUser();
        

        FXMLLoader loader = SceneManager.getLoader("homepage.fxml");
        loader.setController(this.app.getAppController());
        BorderPane root = (BorderPane) SceneManager.getComponent(loader);

        primaryStage.setTitle("Youtube app");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
