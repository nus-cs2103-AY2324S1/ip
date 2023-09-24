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
    private DuchessGui duchessGui = new DuchessGui();
    private FXMLLoader duchessGuiLoader;

    @Override
    public void start(Stage stage) {
        try {
            duchessGuiLoader = new FXMLLoader(Main.class.getResource("/view/Duchess.fxml"));
            VBox vb = duchessGuiLoader.load();

            Scene scene = new Scene(vb);
            stage.setScene(scene);

            duchessGuiLoader.<MainWindowController>getController().setDuchessGui(duchessGui);
            duchessGuiLoader.<MainWindowController>getController().loadTasksFromFile();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        duchessGuiLoader.<MainWindowController>getController().saveTasksToFile();
    }
}


