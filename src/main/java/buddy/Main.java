package buddy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Buddy using FXML.
 */
public class Main extends Application {

    private Buddy buddy = new Buddy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/view/styles/styles.css");
            stage.setTitle("Task Buddy");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBuddy(buddy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (buddy.getExitStatus()) {
            stage.close();
        }
    }
}

