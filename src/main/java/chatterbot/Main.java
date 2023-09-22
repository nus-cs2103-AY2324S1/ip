package chatterbot;

import java.io.IOException;
import java.util.ArrayList;

import chatterbot.data.Task;
import chatterbot.data.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private ChatterBot chatterbot;
    private ArrayList<Task> list = new ArrayList<Task>();
    private TaskList taskList = new TaskList(list);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            chatterbot = new ChatterBot(taskList);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatterBot(chatterbot, taskList);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}