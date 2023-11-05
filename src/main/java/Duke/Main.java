package Duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI application for Duke using FXML.
 * This class launches the Duke chatbot graphical user interface (GUI).
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * The entry point for the Duke GUI application.
     * Initializes the GUI, loads the FXML layout, and sets up the Duke chatbot.
     *
     * @param stage The primary stage for the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Mathan ChatBot");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
