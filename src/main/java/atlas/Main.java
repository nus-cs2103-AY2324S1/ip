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
    static final String SAVE_FILE_DIRECTORY = "./data/";
    static final String SAVE_FILE_NAME = "atlas.txt";

    static final String FXML_FILE_PATH = "/view/MainWindow.fxml";

    private static final int MIN_WINDOW_HEIGHT = 660;
    private static final int MIN_WINDOW_WIDTH = 400;

    private final Atlas atlas = new Atlas(SAVE_FILE_DIRECTORY, SAVE_FILE_NAME);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_FILE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAtlas(atlas);
            stage.show();

            stage.setMinWidth(MIN_WINDOW_WIDTH);
            stage.setMinHeight(MIN_WINDOW_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
