package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * Adapted from https://se-education.org/guides/tutorials/javaFx.html
 */
public class Main extends Application {

    private static final Duke DUKE = new Duke("data/tasks.txt");
    private static final String TITLE = "Chatbot Genos";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            stage.getIcons().add(new Image("/images/Genos.png"));
            fxmlLoader.<MainWindow>getController().setDuke(DUKE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

