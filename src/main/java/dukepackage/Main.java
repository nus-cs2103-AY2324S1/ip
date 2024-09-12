package dukepackage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import toolpackage.Gui;

/**
 * Starts the JavaFX application with the GUI.
 */
public class Main extends Application {

    private Duke duke = new Duke("./data/tasks.txt");

    /**
     * Creates the stage and screen, as well as sets the bot with
     * the Duke class.
     *
     * @param stage The primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Gui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ChampionSOS");
            fxmlLoader.<Gui>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
