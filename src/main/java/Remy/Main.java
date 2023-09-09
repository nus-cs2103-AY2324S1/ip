package remy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import remy.gui.MainWindow;

/**
 * A GUI for Remy using FXML.
 */
public class Main extends Application {

    private static final Path FILEPATH = Paths.get(".", "data", "remy.ser");
    private Remy remy = new Remy(FILEPATH);

    //@@author SE-EDU-reused
    // Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRemy(remy);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@@author
}
