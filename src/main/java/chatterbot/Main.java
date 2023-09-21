package chatterbot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import chatterbot.storage.Storage;
import chatterbot.ui.Ui;
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
    protected static String file = "data/ChatterBot.txt";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Ui ui = new Ui(list);
            Storage storage = new Storage(file, list);


            File f = new File(file);
            if (!f.exists()) {
                System.out.println("Error! Data file not found: " + file);
            } else {
                File folder = f.getParentFile();
                if (!folder.exists()) {
                    System.out.println("Error! No data folder found");
                }
            }

            taskList.initiateTaskList(storage);

            System.out.println(taskList);
            System.out.println(list);

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