package com.ducky;

import java.io.IOException;

import com.ducky.common.Ducky;
import com.ducky.controller.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String DEFAULT_FILE_PATH = "tasks.txt";
    private static final String MAIN_WINDOW_RESOURCE_PATH = "/view/MainWindow.fxml";

    private final Ducky ducky = new Ducky(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_RESOURCE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDucky(ducky);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
