package views;

import com.jfoenix.controls.JFXTabPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Main TabPAne of the app
 */
public class YoutubeTabPane extends JFXTabPane {

    private final static String PATH_ICONS = "/assets/";

    private final static int SIZE_TAB = 40;
    private final static int PADDING_X = 30;
    private final static int PADDING_Y = 15;

    public YoutubeTabPane() {
        super();
        this.getStyleClass().add("youtube-tab-pane");
        this.setMinSize(500, SIZE_TAB + 10);

        this.setPrefSize(500, SIZE_TAB + 10);
        Tab tab = new Tab();
        this.getTabs().add(createTab("search.png"));
        this.getTabs().add(createTab("favorites.png"));
    }

    public Tab createTab(String iconFile) {
        Tab tab = new Tab();
        ImageView v = new ImageView(new Image(PATH_ICONS + iconFile));
        BorderPane box = new BorderPane();
        //v.setPreserveRatio(true);
        v.setFitHeight(SIZE_TAB);
        v.setFitWidth(SIZE_TAB);

        box.setCenter(v);
        box.setPrefSize(SIZE_TAB + PADDING_X, SIZE_TAB + PADDING_Y);
        v.setStyle("-fx-background-color: yellow");

        tab.setGraphic(box);
        tab.getStyleClass().add("youtube-tab");

        return tab;
    }
}
