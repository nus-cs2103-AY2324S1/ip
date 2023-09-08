package userstuff;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class UiLauncher extends Application {

    private static Duke duke;
    private static String greet;

    public static void set(Duke duke, String greet) {
        UiLauncher.duke = duke;
        UiLauncher.greet = greet;
    }
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setGreet(greet);
            duke.setUi(fxmlLoader.<MainWindow>getController());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
