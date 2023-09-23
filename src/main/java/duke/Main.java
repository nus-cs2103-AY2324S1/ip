package duke;

import java.io.IOException;

import duke.ui.TextUi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke;

    private final TextUi textUi;

    /**
     * Main constructor, sets up dependencies.
     * @throws DukeException
     */
    public Main() throws DukeException {
        this.textUi = new TextUi("Pong");
        this.duke = new Duke(this.textUi);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setTextUi(textUi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
