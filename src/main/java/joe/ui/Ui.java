package joe.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import joe.Joe;

/**
 * Represents the user interface for interacting with the program.
 */
public class Ui extends Application {
    private Joe joe = new Joe();

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            if (Math.random() <= 0.1) {
                stage.setTitle("Joe who?");
            } else {
                stage.setTitle("It's Joever");
            }

            fxmlLoader.<MainWindow>getController().setJoe(joe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
