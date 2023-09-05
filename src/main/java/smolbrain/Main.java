package smolbrain;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Smolbrain chatbot using FXML.
 */
public class Main extends Application {

    /**
     * Constructor for this class.
     */
    public Main() {
    }

    /**
     * Starts the main application.
     *
     * @param stage Main primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Smolbrain Chatbot");
            stage.setScene(scene);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/bot.png")));
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().createSmolbrain();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
