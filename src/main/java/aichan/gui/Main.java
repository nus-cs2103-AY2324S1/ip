package aichan.gui;

import java.io.IOException;

import aichan.AiChan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for AiChan using FXML.
 */
public class Main extends Application {

    private AiChan aiChan = new AiChan("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("AiChan");
            fxmlLoader.<MainWindow>getController().setAiChan(aiChan);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
