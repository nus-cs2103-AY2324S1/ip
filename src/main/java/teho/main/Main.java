package teho.main;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

/**
 * A GUI for TehO using FXML.
 */
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications
public class Main extends Application {

    private TehO tehO = new TehO("teho.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("TehO");
            fxmlLoader.<MainWindow>getController().setTehO(tehO);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
