package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The `Main` class serves as the entry point for the Ding chatbot application with a graphical user interface (GUI).
 * It initializes Duke, loads the GUI using JavaFX and FXML, and displays the main application window.
 *
 * This class extends the JavaFX `Application` class and is responsible for launching the GUI.
 */
public class Main extends Application {

    private Duke ding;

    /**
     * Constructs a new `Main` object and initializes the Duke chatbot.
     */
    public Main() {
        ding = new Duke();
    }

    /**
     * The `start` method, required by JavaFX, initializes the GUI by loading the FXML layout and setting up the
     * main application window. It also associates the Ding chatbot
     * with the GUI controller and displays a welcome message.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Ding");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(ding);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
