package roo;

// The following code adapted by https://se-education.org/guides/tutorials/javaFx.html
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Roo using FXML.
 */
public class Main extends Application {

    private Roo roo = new Roo("/roo.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("TaskMasterRoo");
            fxmlLoader.<MainWindow>getController().setDuke(roo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

