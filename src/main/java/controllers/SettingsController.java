package controllers;

import javafx.fxml.FXML;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.stage.DirectoryChooser;
import javafx.scene.control.ButtonType;

import app.Configuration;


public class SettingsController extends Controller{
    
    private String path;
    
    @FXML private JFXButton dbLocButton;
    @FXML private JFXButton saveLocButton;
    @FXML private JFXButton saveButton;
    @FXML private JFXButton resetButton;
    @FXML private JFXButton deleteButton;
    @FXML private JFXTextField dbPathField;
    @FXML private JFXTextField savePathField;

    public SettingsController() {
    }
    
    @FXML
    private void initialize() {
        //this.dbPathField.setText();
        this.dbPathField.setText(Configuration.getInstance().getDbLoc());
        this.savePathField.setText(Configuration.getInstance().getSavePath());
        
        this.dbLocButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                path = pickDir("Choose database directory");
                if (path != null) {
                    dbPathField.setText(path);
                    Configuration.getInstance().setDbPath(path);
                }
            }
        });
        
        this.saveLocButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                path = pickDir("Choose download directory");
                if (path != null) {
                    savePathField.setText(path);
                    Configuration.getInstance().setSavePath(path);
                }
            }
        });
        
        this.resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    Configuration.getInstance().defaultValues();
                    dbPathField.setText(Configuration.getInstance().getDbLoc());
                    savePathField.setText(Configuration.getInstance().getSavePath());
                } catch (Exception excep) {
                    excep.printStackTrace();
                }
            }
        });
        
        this.saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Configuration.getInstance().setDbPath(dbPathField.getText());
                Configuration.getInstance().setSavePath(savePathField.getText());
                Configuration.getInstance().updateSettings();
            }
        });
        
        this.deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("delete");
            }
        });
        
        this.deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                deleteVideos();
            }
        });
    }
    
    private String pickDir(String Title) {
        DirectoryChooser DirectoryChooser = new DirectoryChooser();
        DirectoryChooser.setTitle(Title);

        File selectedFile = DirectoryChooser.showDialog(app.getAppController().getScene().getWindow());
        
        if (selectedFile != null) {
            System.out.println(selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        } else
            return null;
    }
    
    private void deleteVideos() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are going to delete ALL the local videos");
        alert.setContentText("Are you sure you want to delete all the downloaded videos?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //VideoDB.purgeDownloaded();
            Configuration.getInstance().purgeVideoDirectory();
        }
        
    }
}
