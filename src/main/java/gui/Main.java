package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bocchi using FXML.
 */
public class Main extends Application {

    /**
     * Starts the Bocchi GUI application.
     *
     * @param stage The primary stage for the GUI application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bocchi");
            fxmlLoader.<MainWindow>getController().setBocchi(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
