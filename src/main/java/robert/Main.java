package robert;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import robert.ui.MainWindow;

/**
 * A GUI for Robert using FXML.
 *
 * @author Lee Zhan Peng
 */
public class Main extends Application {

    private Robert robert = new Robert("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Robert");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRobert(robert);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
