package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utils.Search;

/**
 * Controller for searching videos on Youtube
 *
 * @author Mcdostone
 * @author Minious
 * @author MartyEz
 * @author Answermouth
 */
public class SearchController extends Controller {

    @FXML private TextField searchField;

    @FXML private Button searchButton;

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
        this.appController.showResults(search.getVideoList());
    }


}
