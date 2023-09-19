package duke;

import java.io.File;
import java.io.IOException;

import duke.gui.MainWindow;
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

    @Override
    public void start(Stage stage) {
        try {
            // Ensure the data directory exists
            File dataDirectory = new File("data");
            if (!dataDirectory.exists()) {
                boolean directoryCreated = dataDirectory.mkdirs();
                if (directoryCreated) {
                    System.out.println("Data directory created: " + dataDirectory.getAbsolutePath());
                } else {
                    System.err.println("Failed to create data directory: " + dataDirectory.getAbsolutePath());
                }
            }

            // Initialize the storage object
            Storage storage = new Storage();

            // Load tasks from the file
            Duke.taskList = storage.loadTasksFromFile();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Sunacchi: Task Manager GU");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
