package duke.gui;
import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class for Duke's graphical user interface (GUI).
 * This class launches the Duke application with a graphical interface using JavaFX and FXML.
 */
public class Main extends Application {

    /**
     * The instance of the Duke application.
     */
    private Duke duke = new Duke("./data/list.txt");

    /**
     * The entry point for the JavaFX application.
     * Initializes the GUI for Duke.
     *
     * @param stage The primary stage for displaying the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set Duke instance for the MainWindow controller
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // Show the main stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the Duke GUI application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
