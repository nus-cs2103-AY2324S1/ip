package duke.duke;

import java.io.IOException;
import java.util.Objects;

import duke.controller.DialogContainer;
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

    /**
     * Starts the Duke bot application.
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *             primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<duke.controller.MainWindow>getController().setDuke(duke);
            fxmlLoader.<duke.controller.MainWindow>getController().showWelcomeMessage();
            fxmlLoader.<duke.controller.MainWindow>getController().loadTasksFromFile();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
