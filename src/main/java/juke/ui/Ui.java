package juke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import juke.exceptions.JukeInitialisationException;
import juke.ui.components.MainWindow;

/**
 * User interface of Juke. Extends {@link Application} to allow
 * JavaFX to run this class like a GUI application.
 */
public class Ui extends Application {
    /** Height of the window. */
    public static final double WINDOW_HEIGHT = 700.0d;

    /** Width of the window. */
    public static final double WINDOW_WIDTH = 450.0d;

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage for this application, onto which
     *     the application scene can be set. Applications may create
     *     other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindowController.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // specify the overall look of the window
            // window is non-resizable with a height of 700px and a width of 450px
            stage.setTitle(MainWindow.APPLICATION_NAME);
            stage.setResizable(false);
            stage.setMinHeight(Ui.WINDOW_HEIGHT);
            stage.setMinWidth(Ui.WINDOW_WIDTH);
            stage.show();
        } catch (IOException e) {
            throw new JukeInitialisationException("I cannot initialise! There was an issue loading the necessary "
                                                          + "FXML files to load up the GUI!");
        }
    }
}
