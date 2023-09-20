package bee;

import java.io.IOException;

import bee.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Bee bee = new Bee();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            scene.getStylesheets().add("./src/main/resources/view/style.css");

            assert fxmlLoader != null : "FXMLLoader failed to load MainWindow.fxml";

            fxmlLoader.<MainWindow>getController().setBee(bee);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
