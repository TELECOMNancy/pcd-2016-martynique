package controllers;

import app.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import models.Search;

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

    @FXML
    private Button searchButton;

    public SearchController() {}

    @FXML
    private void initialize() {
        this.searchButton.requestFocus();
        this.searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    System.out.println("#QUERY '" + searchField.getText() + "'");
                    search();
                }
            }
        });

        this.searchButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                search();
            }
        });
    }

    private void search() {
        Search search = new Search(searchField.getText());
        search.executeApiRequest();
        search.printResult();
        SceneManager.getInstance(null).showResults(search.searchResultList());
    }
}
