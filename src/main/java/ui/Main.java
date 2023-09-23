package ui;

import java.io.IOException;

import chatbot.evan.Evan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Evan evan = new Evan();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow window = fxmlLoader.<MainWindow>getController();
            window.setEvan(evan);
            window.onStart();
            stage.show();

            //Initialise introductory message

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
