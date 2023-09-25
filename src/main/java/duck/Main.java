package duck;

import java.io.IOException;

import duck.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Main class with Java Fx
 */
public class Main extends Application {
    private Duck duke = new Duck();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            stage.setTitle("Duke");
            stage.setMinHeight(640.0);
            stage.setMinWidth(515.0);
            ap.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            ap.prefWidthProperty().bind(stage.widthProperty());
            ap.prefHeightProperty().bind(stage.heightProperty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
