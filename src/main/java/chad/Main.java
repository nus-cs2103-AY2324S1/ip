package chad;

import java.io.IOException;

import chad.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import chad.chatengine.ChatEngine;

/**
 * The entry point for the Chad application.
 */
public class Main extends Application{
    private ChatEngine chad = new ChatEngine("./data/tasks.txt");

    @Override
    public void start(Stage stage) {
         try {
            // Load the user interface as a scene and set it on stage.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChad(chad);
            stage.show();
            stage.setTitle("ChadBot Task Manager");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
