package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import duke.ui.MainWindow;

public class Main extends Application {
    private Duke rion = new Duke("./data/tasks.txt");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Rion");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRion(rion);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
