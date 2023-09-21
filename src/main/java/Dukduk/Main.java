package dukduk;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dukduk using FXML.
 */
public class Main extends Application {

    private Dukduk dukduk = new Dukduk();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("dukduk");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDukduk(dukduk, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

