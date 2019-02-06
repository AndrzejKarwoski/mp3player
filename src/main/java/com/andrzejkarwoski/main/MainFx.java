package com.andrzejkarwoski.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainFx extends Application {
    @Override
    public void start(Stage primaryStage) {
        final String appName = "MP3Player by IIoo/Andrzej Karwoski";
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                "/MainPane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle(appName);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
