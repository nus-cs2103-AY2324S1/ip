package seedu.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            // Display Duke's welcome message in the GUI
            Label welcomeLabel = new Label("____________________________________________________________\n" +
                    "Hello! I'm Dookie.\n" +
                    "What can I do for you?\n" +
                    "____________________________________________________________");
            Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
            fxmlLoader.<MainWindow>getController().getDialogContainer().getChildren().add(DialogBox.getDukeDialog(welcomeLabel.getText(), dukeImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
