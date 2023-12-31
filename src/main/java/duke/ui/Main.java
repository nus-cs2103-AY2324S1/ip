package duke.ui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the main application class for the Duke program with a GUI.
 * This class initializes the primary window and sets up the necessary components.
 */
public class Main extends Application {

    private static final String TASKS_FILE_PATH = "data/tasks.txt";
    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private static final String ICON_IMAGE_PATH = "/images/icon.png";
    private static final String WINDOW_TITLE = "DaDaYuan";

    private final Duke duke = new Duke(TASKS_FILE_PATH);

    /**
     * Starts the application by initializing the main window.
     *
     * @param stage The primary stage of the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            initializeMainWindow(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up and initializes the main window for the application.
     *
     * @param stage The primary stage of the application.
     * @throws IOException If there's an error loading the FXML file.
     */
    private void initializeMainWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = loadFxml(MAIN_WINDOW_FXML_PATH);
        AnchorPane anchorPane = fxmlLoader.load();

        configureStageWithScene(stage, new Scene(anchorPane));
        injectDukeIntoController(fxmlLoader);
        setWindowIcon(stage, ICON_IMAGE_PATH);
        stage.setTitle(WINDOW_TITLE);
        stage.show();
    }

    private FXMLLoader loadFxml(String path) {
        return new FXMLLoader(Main.class.getResource(path));
    }

    private void configureStageWithScene(Stage stage, Scene scene) {
        stage.setScene(scene);
    }

    private void injectDukeIntoController(FXMLLoader loader) {
        loader.<MainWindow>getController().setDuke(duke);
    }

    private void setWindowIcon(Stage stage, String iconPath) {
        stage.getIcons().add(new Image(iconPath));
    }
}
