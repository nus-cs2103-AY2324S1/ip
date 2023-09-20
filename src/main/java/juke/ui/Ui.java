package juke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import juke.commons.exceptions.JukeInitialisationException;

/**
 * User interface of Juke.
 */
public class Ui extends Application {
    /** Name of the application. */
    public static final String APPLICATION_NAME = "Juke";

    /** Height of the window. */
    public static final double WINDOW_HEIGHT = 700.0d;

    /** Width of the window. */
    public static final double WINDOW_WIDTH = 450.0d;

    //@@author asdfghjkxd-reused
    // Code is largely reused from https://se-education.org/guides/tutorials/javaFxPart2.html and
    // https://se-education.org/guides/tutorials/javaFxPart4.html with very minor modifications.
    /**
     * Starts the JavaFX application.
     *
     * @param stage the scaffold of the application, whereby Nodes/widgets will be displayed.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // specify the overall look of the window
            // window is non-resizable with a height of 700px and a width of 450px
            stage.setTitle(Ui.APPLICATION_NAME);
            stage.setResizable(false);
            stage.setMinHeight(Ui.WINDOW_HEIGHT);
            stage.setMinWidth(Ui.WINDOW_WIDTH);
            stage.show();
        } catch (IOException e) {
            throw new JukeInitialisationException("I cannot initialise! There was an issue loading the necessary "
                                                          + "FXML files to load up the GUI!");
        }
    }
    //@@author
}
