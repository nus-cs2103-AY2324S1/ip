package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();

    /**
     * Starts the GUI.
     *
     * @param stage The stage to be used.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("KS Task Manager");
        stage.show();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setOnHidden(event -> {
                duke.updateTaskList();
                fxmlLoader.<MainWindow>getController().getExitMessage();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
