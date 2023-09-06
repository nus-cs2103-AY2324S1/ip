package ekud.ui;
// Main.java class is taken from https://se-education.org/guides/tutorials/javaFxPart4.html
import java.io.IOException;

import ekud.Ekud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML. (https://se-education.org/guides/tutorials/javaFxPart4.html)
 */
public class Main extends Application {

    private Ekud duke = new Ekud();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ekud To-Dos"); // Add title
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().greetUser(); // Add greeting dialog box
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
