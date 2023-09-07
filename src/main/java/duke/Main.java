package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private Duke duke = new Duke();
    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create an instance of MainWindow
            mainWindow = fxmlLoader.getController();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            mainWindow.initialize(stage);

            // Set the Duke instance for MainWindow
            mainWindow.setDuke(duke);


            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
