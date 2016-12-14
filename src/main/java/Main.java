import app.App;
import app.SceneManager;
import controllers.AppController;
import db.FavoriteDB;
import db.SuggestionDB;
import db.VideoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Favorite;
import models.User;
import models.Video;

import java.util.List;


public class Main extends Application {

    private final App app = App.getInstance();

    private void initDB() {
        VideoDB.createTable();
        //FavoriteDB.createTable();
        //SuggestionDB.createTable();
    }

    private void initUser() {
        List<Favorite> fav = new VideoDB().getFavorites();
        for(Favorite f: fav)
            this.app.getUser().addFavorite(f);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.initDB();
        this.initUser();

        FXMLLoader loader = SceneManager.getLoader("homepage.fxml");
        loader.setController(this.app.getAppController());
        BorderPane root = (BorderPane) SceneManager.getComponent(loader);

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
