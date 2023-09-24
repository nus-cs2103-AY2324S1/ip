package Forgotten.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Forgotten.GUI.ForgottenGUI using FXML.
 */
public class Main extends Application {
    private ForgottenGUI forgottenGUI = new ForgottenGUI();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Forgotten");
            fxmlLoader.<MainWindow>getController().setForgottenGUI(forgottenGUI);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}