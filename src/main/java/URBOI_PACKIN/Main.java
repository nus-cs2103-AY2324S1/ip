package URBOI_PACKIN;

import java.io.IOException;

import URBOI_PACKIN.UI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for URBOI_PACKIN.ResponseController using FXML.
 */
public class Main extends Application {

    private ResponseController responseController = new ResponseController();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(responseController);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}