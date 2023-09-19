package chadbod;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class represents a GUI for ChadBod using FXML.
 */
public class Main extends Application {
    private static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";
    private ChadBod chadbod = new ChadBod();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("ChadBod");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChadBod(chadbod);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
