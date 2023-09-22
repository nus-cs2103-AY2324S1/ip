package duke;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "FXMLLoader should not be null.";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane loaded from FXML should not be null.";
            Scene scene = new Scene(ap);
            assert scene != null : "Scene should not be null.";
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            assert fxmlLoader.getController() != null : "MainWindow controller should not be null.";
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert duke != null : "Duke object should not be null.";
    }
}




