package jeoe;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jeoe using FXML.
 */
public class Main extends Application {

    private Jeoe jeoe = new Jeoe();

    /**
     * Starts the GUI by taking in a stage.
     *
     * @param stage Stage to be taken in for display on the GUI.
     */
    @Override
    public void start(Stage stage) {
        assert jeoe != null : "jeoe program should not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "fxml loader has to exist";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "anchor pane has to exist";
            Scene scene = new Scene(ap);
            assert scene != null : "a scene has to exist";
            stage.setScene(scene);
            stage.setTitle("Jeoe application");
            assert stage != null : "a stage has to exist";
            fxmlLoader.<MainWindow>getController().setJeoe(jeoe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
