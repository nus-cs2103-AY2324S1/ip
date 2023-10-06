package taskmate.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taskmate.ui.MainWindow;

/**
 * A GUI for TaskMate using FXML.
 */
public class Main extends Application {

    private final TaskMate taskMate = new TaskMate();

    /**
     * Sets the stage and links the FXML, TaskMate, and title components together.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *     Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("TaskMate GUI");
            fxmlLoader.<MainWindow>getController().setTaskMate(taskMate);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
