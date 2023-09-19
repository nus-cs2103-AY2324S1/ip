import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Image backgroundImage;

    @Override public void init() {
        backgroundImage = new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg");
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}