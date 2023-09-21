package duke.ui.graphic;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Serves as entry point for the GUI programme, to be invoked by Application::launch.
 */
public class DukeApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeApplication.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            MainWindow graphicInterface = fxmlLoader.getController();
            new Duke(false, graphicInterface);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/icon.png"));
            stage.setTitle("Quack-NKN");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
