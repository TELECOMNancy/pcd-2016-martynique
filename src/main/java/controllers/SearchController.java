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
    
    @FXML private Button returnButton;

    @FXML
    private void initialize() {
        setReturnVisible(false);
        
        this.returnButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                app.getAppController().showHome();
                setReturnVisible(false);
            }
        });
        
        this.searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    System.out.println("#QUERY '" + searchField.getText() + "'");
                    setReturnVisible(true);
                    search();
                }
            }
        });
        
        this.searchButton.requestFocus();
        this.searchButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setReturnVisible(true);
                search();
            }
        });
        
        
    }

    private void setReturnVisible(boolean bool) {
        this.returnButton.setVisible(bool);
        this.returnButton.setManaged(bool);
    }
    
    private void search() {
        Search search = new Search(searchField.getText());
        search.executeApiRequest();
        app.getAppController().showResults(search.getVideoList());
    }


}
