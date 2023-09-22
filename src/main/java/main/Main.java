package main;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import ui.MainWindow;
/**
 * A GUI for Alpha using FXML.
 *
 * @author Wong Joon Hung
 */
public class Main extends Application {

    private Alpha alpha = new Alpha();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(alpha);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
