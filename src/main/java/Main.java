import exception.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.Ui;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Duke duke;
    {
        try {
            duke = new Duke();
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            // Assuming MainWindow controller has a method to display a message
            String welcomeMessage = Ui.showWelcome();
            fxmlLoader.<MainWindow>getController().displayMessage(welcomeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}