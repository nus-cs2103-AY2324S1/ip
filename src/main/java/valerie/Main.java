package valerie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Valerie using FXML.
 */
public class Main extends Application {

    private final List<Task> userList = new ArrayList<>();
    private final TaskList taskList = new TaskList(userList);
    private final Valerie valerie = new Valerie("./data/valerie.txt", taskList);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setValerie(valerie);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

