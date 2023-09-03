package bob;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Bob bob = new Bob();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setRoot(new AnchorPane());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bob");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            fxmlLoader.<MainWindow>getController().setBob(bob);
            stage.show();
            fxmlLoader.<MainWindow>getController().greetUser();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
