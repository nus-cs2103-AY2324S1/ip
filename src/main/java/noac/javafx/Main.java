package noac.javafx;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noac.Noac;

/**
 * A GUI for NOAC using FXML.
 */
public class Main extends Application {

    private Noac noac = new Noac();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Noac");
            fxmlLoader.<MainWindow>getController().setNoac(noac);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
