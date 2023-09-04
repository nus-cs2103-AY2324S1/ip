package duchess;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private DuchessGUI duchessGUI = new DuchessGUI();
    private FXMLLoader duchessGUILoader;

    @Override
    public void start(Stage stage) {
        try {
            duchessGUILoader = new FXMLLoader(Main.class.getResource("/view/Duchess.fxml"));
            VBox vb = duchessGUILoader.load();

            Scene scene = new Scene(vb);
            stage.setScene(scene);

            duchessGUILoader.<MainWindowController>getController().setDuchessGUI(duchessGUI);
            duchessGUILoader.<MainWindowController>getController().loadTasksFromFile();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        duchessGUILoader.<MainWindowController>getController().saveTasksToFile();
    }
}


