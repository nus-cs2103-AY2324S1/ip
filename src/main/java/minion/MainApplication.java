package minion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import minion.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class MainApplication extends Application {

    private Minion minion = new Minion("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Minion Bot");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setupChatbot(minion, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
