package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Created by mcdostone on 16/12/16.
 */
public class AskNewPlaylistController extends Controller {


    @FXML
    public void initialize() {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("Créer une playlist"));
        JFXTextField field = new JFXTextField();
        layout.setBody(field);
        JFXButton create = new JFXButton("Créer");
        layout.setActions(create);


        JFXDialog dialog = new JFXDialog(null, layout, JFXDialog.DialogTransition.CENTER);

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });


        dialog.show();


    }
}
