package nobita;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nobita.ui.MainWindow;

/**
 * A GUI for Nobita using FXML.
 *
 * @author Zheng Chenglong
 */
public class Main extends Application {

    /** Create Nobita App to interact with */
    private final Nobita nobita = new Nobita();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNobita(nobita);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
