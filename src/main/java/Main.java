import java.io.IOException;

import duke.components.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private ButlerBot bot = new ButlerBot();

    public Main() throws DukeException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            assert scene != null; // This ensures that the Scene instance is instantiated properly.
            stage.setScene(scene);
            assert bot != null; // This ensures that the ButlerBot instance is instantiated properly.
            fxmlLoader.<MainWindow>getController().setButlerBot(bot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
