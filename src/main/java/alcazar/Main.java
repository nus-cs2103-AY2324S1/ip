package alcazar;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * A GUI for Alcazar using FXML.
 */
public class Main extends Application {

    private Alcazar alcazar = new Alcazar();

    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "FXML source not found";
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Alcazar");
            fxmlLoader.<MainWindow>getController().setAlcazar(alcazar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}