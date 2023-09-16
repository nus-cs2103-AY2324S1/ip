package dre;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GUI for Dre using FXML.
 */
public class Main extends Application {

    private Dre dre = new Dre();

    /**
     * Starts and displays the Dre application GUI.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDre(dre);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
