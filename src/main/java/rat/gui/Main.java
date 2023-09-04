package rat.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import rat.Rat;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Rat rat = new Rat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Rat");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setRat(rat);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
