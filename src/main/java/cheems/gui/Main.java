package cheems.gui;

import java.io.IOException;

import cheems.Cheems;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Cheems using FXML.
 */
public class Main extends Application {

    private Cheems cheems = new Cheems();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCheems(cheems);
            stage.show();

            fxmlLoader.<MainWindow>getController().showWelcomeDialog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
