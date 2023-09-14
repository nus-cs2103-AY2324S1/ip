package taskmaster.ui;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taskmaster.Taskmaster;

import java.io.IOException;

/**
 * A launcher class to workaround classpath issues.
 */
public class Main extends Application{
    private Taskmaster taskmaster = new Taskmaster();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskmaster(taskmaster);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}