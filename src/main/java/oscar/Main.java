package oscar;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oscar.ui.MainWindow;

/**
 * A GUI for Oscar using FXML.
 */
public class Main extends Application {

    private final Oscar oscar = new Oscar(Oscar.FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            ap.heightProperty().add(scene.getHeight());
            ap.widthProperty().add(scene.getHeight());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setOscar(oscar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

