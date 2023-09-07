package app;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.Duke;
import duke.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The `Main` class serves as the entry point for the Duke chatbot application with a graphical user interface (GUI).
 * It initializes Duke, loads the GUI using JavaFX and FXML, and displays the main application window.
 *
 * This class extends the JavaFX `Application` class and is responsible for launching the GUI.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Main extends Application {

    private Duke duke;

    /**
     * Constructs a new `Main` object and initializes the Duke chatbot.
     *
     * @throws DukeException If there is an issue with Duke's initialization.
     * @throws FileNotFoundException If the task data file is not found.
     */
    public Main() throws DukeException, FileNotFoundException {
        duke = new Duke("data/tasks.txt");
    }

    /**
     * The `start` method, required by JavaFX, initializes the GUI by loading the FXML layout and setting up the
     * main application window. It also associates the Duke chatbot
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
            stage.setTitle("Bloop Bloop");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
