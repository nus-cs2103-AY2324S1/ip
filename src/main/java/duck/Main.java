package duck;

import java.io.IOException;

import duck.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duck using FXML.
 */
public class Main extends Application {

    public Main() {
    }

    private Duck duke = new Duck();

    @Override
    public void start(Stage stage) {
        try {
            System.out.println(Main.class.getPackageName());
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuck(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
