package qi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Qi using FXML.
 */
public class Main extends Application {

    private Qi qi = new Qi("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Qi - The ChatBot");
            Image icon = new Image(Main.class.getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(icon);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setQi(qi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

