package oreo;

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
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            MainWindow gui = fxmlLoader.getController();
            Oreo oreo = new Oreo("/Users/daniel/Desktop/CS2103T/iP/src/main/java/data/oreo.txt",
                    gui);
            stage.setTitle("Oreo");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
