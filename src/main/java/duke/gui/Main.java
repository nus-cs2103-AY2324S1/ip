package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents GUI for Duke using FXML.
 * Reused from this <a href="https://se-education.org/guides/tutorials/javaFx.html">tutorial</a>
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    /**
     * Starts the GUI.
     * @param stage Stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}
