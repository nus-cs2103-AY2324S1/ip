package dook.gui;
import java.io.IOException;

import dook.Dook;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Dook dook = new Dook();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Dook");
            fxmlLoader.<MainWindow>getController().setDuke(dook);
            stage.show();
            fxmlLoader.<MainWindow>getController().startUpDook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
