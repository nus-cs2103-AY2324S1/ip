package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static Path tempFilePath = Paths.get("./data/temp.txt");
    private Duke duke = new Duke(tempFilePath);
    private TaskList tasks = duke.getTasks();
    private Storage storage = duke.getStorage();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setTasks(tasks);
            fxmlLoader.<MainWindow>getController().setStorage(storage);
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
