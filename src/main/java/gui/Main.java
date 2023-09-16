package gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    public static String loadOutput = "";

    @Override
    public void start(Stage stage) {
        // Preload chatbot with data from storage file, saves output as .
        loadOutput = duke.loadData();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            stage.setTitle("Jyuuni");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
