package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();
    private Image appIcon = new Image(this.getClass().getResourceAsStream("/images/error404.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setScene(scene);
            stage.setTitle("Task404Bot");
            stage.setResizable(false);
            stage.getIcons().add(appIcon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
