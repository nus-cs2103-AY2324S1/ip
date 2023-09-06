
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import main.KniazSession;

/**
 * Entry point for the Kniaz program
 */
public class Kniaz extends Application{

    /**
     * Main method of Kniaz. Runs the session.
     * @param args command-line arguments, currently not used.
     */
    public static void main(String[] args) {
        KniazSession.init().run();
    }

    @Override
    //@@author Jeffry Lium
    //Reused from https://se-education.org/guides/tutorials/javaFxPart1.html
    // with minor modifications
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    //@@author





}
