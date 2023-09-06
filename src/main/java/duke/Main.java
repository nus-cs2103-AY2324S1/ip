package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    // The Duke instance for handling user interactions.
    private Duke duke = new Duke();

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create a new scene with the main window content
            Scene scene = new Scene(ap);

            // Set the scene for the primary stage
            stage.setScene(scene);

            // Get the controller for the main window
            MainWindow mainWindowController = fxmlLoader.getController();

            // Set the Duke instance in the main window controller
            mainWindowController.setDuke(duke);

            // Display a welcome message in the main window
            mainWindowController.welcomeMessage();

            // Show the primary stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}