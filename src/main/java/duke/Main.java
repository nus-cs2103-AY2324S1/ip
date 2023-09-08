package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the Duke application.
 * It sets up the JavaFX user interface and initializes the bot.
 *
 */
public class Main extends Application {
    private Duke duke = new Duke("data/tasks.txt");

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create a new scene with the loaded AnchorPane
            Scene scene = new Scene(ap);

            // Set the scene for the primary stage
            stage.setScene(scene);

            // Get the controller associated with the FXML and set the Duke instance
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // Print a greeting message using the controller
            fxmlLoader.<MainWindow>getController().printGreeting();

            // Show the primary stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}