package ui;

import java.io.IOException;

import jarvis.Jarvis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jarvis using FXML.
 */
public class Main extends Application {

    private Jarvis jarvis = new Jarvis("data/jarvislist.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJarvis(jarvis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}