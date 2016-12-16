package controllers;

import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;

public class NewPlaylistController {

    @FXML private StackPane root;
    @FXML private JFXDialog dialog;

    @PostConstruct
    public void init() {
        root.getChildren().remove(dialog);
/*
        centerButton.setOnMouseClicked((e)->{
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
        });

        topButton.setOnMouseClicked((e)->{
            dialog.setTransitionType(DialogTransition.TOP);
            dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
        });

        rightButton.setOnMouseClicked((e)->{
            dialog.setTransitionType(DialogTransition.RIGHT);
            dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
        });

        bottomButton.setOnMouseClicked((e)->{
            dialog.setTransitionType(DialogTransition.BOTTOM);
            dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
        });

        leftButton.setOnMouseClicked((e)->{
            dialog.setTransitionType(DialogTransition.LEFT);
            dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
        });

        acceptButton.setOnMouseClicked((e)->{
            dialog.close();
        });*/
    }


}