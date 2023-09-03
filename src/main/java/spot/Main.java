package spot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A GUI for Spot using FXML.
 */
public class Main extends Application {

    private Spot spot = new Spot();

    /**
     * Starts up the Spot chatbot.
     *
     * @param stage Stage to display GUI nodes.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSpot(spot);
            stage.setOnCloseRequest((WindowEvent event) -> {
                spot.getResponse("bye");
            });
            stage.setTitle("Spot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
