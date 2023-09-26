package oreo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import oreo.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            MainWindow gui = fxmlLoader.getController();
            Oreo oreo = new Oreo("./oreo.txt",
                    gui);
            stage.setTitle("Oreo");
            stage.getIcons().add(new Image("/images/icon.png"));
            stage.setScene(scene);
            stage.setMinHeight(700);
            stage.setMinWidth(500);
            stage.setMaxWidth(500);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
