package app;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

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

    public static Parent getComponent(FXMLLoader loader) {
        Parent component = null;
        try {
            component = loader.load();
            System.out.println(component.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return component;
    }

    /**
     * Modify the current scene of the stage.
     * @param scene The new scene
     */
    private void setScene(Scene scene) {

    }

    public static FXMLLoader getLoader(String filename) {
        return new FXMLLoader(SceneManager.class.getResource(PATH_FXML + filename));
    }

    /**
     * @param filename FXML file to load.
     * @return The Node
     */
    private Parent loadFXML(String filename) {
        try {
            return this.getLoader(filename).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
