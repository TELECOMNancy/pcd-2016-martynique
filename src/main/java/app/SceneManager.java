package app;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    /** Main stage */
    private Stage stage;

    private static final String PATH_FXML = "/fxml/";

    public SceneManager(Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
    }

    /** Displays the homepage. */
    public void startHomepage() {
        Parent root = this.loadFXML("homepage.fxml");
        this.stage.setTitle("Youtube app");
        this.stage.setMinHeight(450);
        this.stage.setMinWidth(450);
        this.setScene(new Scene(root));
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

}
