package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.GigaChadBot;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final GigaChadBot duke = new GigaChadBot();

    /**
     * Starts the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("GigaChadbot");
            fxmlLoader.<MainWindow>getController().setBot(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
