import app.SceneManager;
import db.FavoriteDB;
import db.SuggestionDB;
import db.VideoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_Form_Test extends Application {

    private void initDB() {
        SuggestionDB.createTable();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
    	this.initDB();
    	
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
