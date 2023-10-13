package pau.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pau.Pau;

/**
 * A GUI for Pau using FXML.
 */
public class Main extends Application {

    private Pau pau = new Pau("./data/paulist.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("PAU");
            fxmlLoader.<MainWindow>getController().setPau(pau);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
