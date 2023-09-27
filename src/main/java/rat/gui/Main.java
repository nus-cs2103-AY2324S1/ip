package rat.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rat.Rat;

/**
 * A GUI for Rat using FXML.
 * This class brings together Rat's logic and the GUI.
 */
public class Main extends Application {

    /**
     * The Rat instance to be used by the GUI.
     */
    private Rat rat = new Rat();

    /**
     * Starts the GUI.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Rat");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setRat(rat);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
