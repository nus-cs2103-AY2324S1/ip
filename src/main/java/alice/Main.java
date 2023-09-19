package alice;

import java.io.IOException;

import alice.ui.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Alice using FXML.
 */
public class Main extends Application {
    // The instance of Alice that will be used throughout the program
    private final Alice alice = new Alice();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Alice Task Manager");
            fxmlLoader.<MainWindow>getController().setAlice(alice);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

