package socrates;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socrates.gui.MainWindow;

/**
 * Represents a GUI for Socrates
 */
public class Main extends Application {

    private final Socrates socrates = new Socrates();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSocrates(socrates);
            stage.show();

            stage.setTitle("SoCrates");
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
