package anya;

import java.io.IOException;

import anya.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Anya anya = new Anya();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(anya.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Anya Forger");
            stage.getIcons().add(new Image("/images/anya.png"));
            fxmlLoader.<MainWindow>getController().setAnya(anya);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}