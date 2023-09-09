package duke.gui;

import java.io.IOException;

import duke.Bobi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class is a GUI for Bobi using FXML.
 *
 * @author ruo-x
 */
public class Main extends Application {
    private Bobi bobi = new Bobi();

    /**
     * Starts Bobi's GUI, load all components of the GUI.
     *
     * @param stage the primary stage for this application, onto which
     *     the application scene can be set.
     *     Applications may create other stages, if needed, but they will not be
     *     primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Loading layout of GUI
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBobi(bobi);
            stage.setTitle("Bobi");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
