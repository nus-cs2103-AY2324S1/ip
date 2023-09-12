package simon;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Simon using FXML.
 */
public class Main extends Application {

    private Simon simon = new Simon();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/view/styles.css"))
                    .toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(simon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
