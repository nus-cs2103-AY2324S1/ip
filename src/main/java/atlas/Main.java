package atlas;

import java.io.IOException;

import atlas.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for initialising Atlas's GUI
 */
public class Main extends Application {
    private final Atlas atlas = new Atlas("./data/", "atlas.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAtlas(atlas);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
