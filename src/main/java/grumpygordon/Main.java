package grumpygordon;

import java.io.IOException;

import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.ui.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    /**
     * Grumpy Gordon instance.
     */
    private GrumpyGordon grumpyGordon;

    /**
     * Starts the GUI.
     *
     * @param stage Stage to start GUI on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            grumpyGordon = new GrumpyGordon();
            fxmlLoader.<MainWindow>getController().setGrumpyGordon(grumpyGordon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GrumpyGordonException e) {
            e.printStackTrace();
        }
    }
}
