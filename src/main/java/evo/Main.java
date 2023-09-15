package evo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Evo using FXML.
 */
public class Main extends Application {
    /** An instance of the Evo application */
    private Evo evo = new Evo();

    /**
     * The entry point for starting the JavaFX application.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEvo(evo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
