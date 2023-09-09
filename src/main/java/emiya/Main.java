package emiya;

import java.io.IOException;

import emiya.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Emiya taskbot.
 */
public class Main extends Application {

    /**
     * Constructor for this class.
     */
    public Main() {
    }

    /**
     * Starts the main application.
     *
     * @param stage Main primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setTitle("Emiya Task Manager");
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().createEmiyaInstance();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
