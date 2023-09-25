package bert.ui;

import java.io.IOException;

import bert.Bert;
import bert.ui.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bert using FXML.
 */
public class Main extends Application {

    private Bert bert = new Bert();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBert(bert);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
