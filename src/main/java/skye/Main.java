package skye;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the entry point of the JavaFX Desktop GUI for Skye.
 */
public class Main extends Application {

    private Skye skye = new Skye("data/tasks.txt");

    /**
     * {@inheritDoc}
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the user interface as a scene and set it on stage.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Pass the context of the Skye chatbot to MainWindow's controller.
            fxmlLoader.<MainWindow>getController().setSkye(skye);

            // Display the window to the user.
            stage.show();

            // Set the title of the application.
            stage.setTitle("Skye");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
