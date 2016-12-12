package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for searching videos on Youtube
 *
 * @author Mcdostone
 * @author Minious
 * @author MartyEz
 * @author Answermouth
 */
public class SearchController {

    @FXML TextField searchField;

    public SearchController() {}

    @FXML
    private void initialize() {
        this.searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER))
                    System.out.println("#QUERY '" + searchField.getText() + "'");
            }
        });
    }

}
