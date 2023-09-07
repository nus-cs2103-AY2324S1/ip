package duke;

import static duke.Duke.storage;
import static duke.Storage.loadTasksFromFile;

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
    private static int index = 0;
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            File directory = new File(String.valueOf(storage.path.getParent())); // Get the parent directory
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    Ui.print("Directory created: " + directory.getAbsolutePath());
                } else {
                    Ui.print("Failed to create directory: " + directory.getAbsolutePath());
                }
            }

            if (!storage.getFileExists()) {
                try {
                    boolean fileCreated = storage.f.createNewFile();
                    if (fileCreated) {
                        Ui.print("File created: " + storage.path);
                    } else {
                        Ui.print("Failed to create file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (storage.getFileExists()) {
                Duke.taskList = loadTasksFromFile(String.valueOf(storage.path));
                index = Duke.taskList.size(); // Set the list size to the loaded tasks count
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
