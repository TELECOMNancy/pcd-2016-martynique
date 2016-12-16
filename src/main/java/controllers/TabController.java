package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Abstract controller for a tab
 */
public abstract class TabController extends Controller {

    @FXML protected BorderPane tab;
    @FXML protected Label tabName;

    @FXML public void initialize() {
        this.tabName.setText(this.getTabName());

    }

    public abstract String getTabName();

}
