package peko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The `Main` class serves as the main entry point for the Peko JavaFX application.
 * It extends the JavaFX `Application` class and sets up the application's user interface.
 */
public class Main extends Application {
    private Peko peko = new Peko();

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPeko(peko);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
