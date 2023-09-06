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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJeoe(jeoe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
