package phi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import phi.ui.MainWindow;

/**
 * A GUI for Phi using FXML.
 */
public class Main extends Application {

    private Phi phi = new Phi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/phi.jpeg"));
            stage.setTitle("PHI");
            fxmlLoader.<MainWindow>getController().setPhi(phi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}