package brandon.chatbot;

import java.io.IOException;

import brandon.chatbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for ekuD using FXML.
 */
public class Main extends Application {

    private Ekud ekuD = new Ekud();

    @Override
    public void start(Stage stage) {
        try {
            ekuD.setTasks(ekuD.getStorage().load());
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(ekuD);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
