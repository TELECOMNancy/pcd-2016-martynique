package app;

import com.google.api.services.youtube.model.SearchResult;
import controllers.ResultsController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Manages all scenes of the app.
 *
 * @author Mcdostone
 * @author Minious
 * @author MartyEz
 * @author Answermouth
 */
public class SceneManager {

    private static SceneManager sceneManager;


    /** Main stage */
    private Stage stage;

    private BorderPane pane;

    private static final String PATH_FXML = "/fxml/";

    private SceneManager(Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
    }

    /** Displays the homepage. */
    public void startHomepage() {
        this.pane = (BorderPane) this.loadFXML("homepage.fxml");

        this.stage.setTitle("Youtube app");
        this.stage.setMinHeight(450);
        this.stage.setMinWidth(450);
        this.setScene(new Scene(this.pane));
    }

    /** Displays the homepage. */
    public void showResults(List<SearchResult> results) {
        FXMLLoader loader = this.getLoader("results.fxml");
        ResultsController ctrl = new ResultsController(results);
        loader.setController(ctrl);

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.pane.setCenter(root);
    }



    /**
     * Modify the current scene of the stage.
     * @param scene The new scene
     */
    private void setScene(Scene scene) {
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        this.stage.requestFocus();
        this.stage.show();
    }

    public FXMLLoader getLoader(String filename) {
        return new FXMLLoader(getClass().getResource(PATH_FXML + filename));
    }

    /**
     * @param filename FXML file to load.
     * @return The Node
     */
    protected Parent loadFXML(String filename) {
        try {
            return this.getLoader(filename).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static SceneManager getInstance(Stage stage) {
        if(sceneManager == null)
            SceneManager.sceneManager = new SceneManager(stage);
        return SceneManager.sceneManager;
    }

}
