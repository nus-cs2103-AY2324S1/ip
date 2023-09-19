package sillybot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sillybot.SillyBot;

/**
 * A GUI for SillyBot using FXML.
 */
public class Main extends Application {

    private final SillyBot sillyBot = new SillyBot("data/stored_tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(sillyBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
