package com.andrzejkarwoski.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPaneController implements Initializable {

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem readmeMenuItem;

    @FXML
    private MenuItem fileMenuItem;

    @FXML
    private MenuItem dirMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    public MenuItem getFileMenuItem() {
        return fileMenuItem;
    }

    public MenuItem getDirMenuItem() {
        return dirMenuItem;
    }

    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureMenu();
    }

    private void configureMenu() {
        closeMenuItem.setOnAction(x -> Platform.exit());

        readmeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Parent parent = null;
                try {
                    System.out.println("DEBUGUJE");
                    parent = FXMLLoader.load(getClass().getResource(
                            "/ReadMe.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setTitle("README");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e ) {
                    e.printStackTrace();

                }
            }
        });
    }
}
