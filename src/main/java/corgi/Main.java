package corgi;

import java.io.IOException;

import corgi.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Corgi using FXML.
 */
public class Main extends Application {

    private Corgi corgi = new Corgi();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Corgi - The most intelligent chatbot");

        Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.jpg"));
        stage.getIcons().add(icon);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCorgi(corgi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

