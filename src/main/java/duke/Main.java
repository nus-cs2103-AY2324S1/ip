package duke;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The `Main` class serves as the entry point for the Duke application's GUI.
 * It initializes the application, loads tasks from a data file,
 * and sets up the user interface.
 */
public class Main extends Application {

    private static Duke duke = new Duke();
    public static TaskList taskList = Duke.taskList;
    
    /**
     * Forms the main entry point of the Duke GUI application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            Storage storage = new Storage();
            Storage.createDataLocation();
            // Load tasks from the data file into the TaskList
            storage.loadTasksFromFile(taskList);
            List<Task> allTasks = taskList.getTasks();
            assert storage != null : "Storage should not be null";
            assert allTasks != null : "All tasks should not be null";
            Parser parser = new Parser();
            if (allTasks.size() == 0) {
            parser.run = 1;
            }
            System.out.flush();
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

