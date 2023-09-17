package seedu.dookie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * A GUI for Dookie using FXML.
 */
public class Main extends Application {

    private Dookie dookie = new Dookie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDookie(dookie);
            stage.show();

            // Display Dookie's welcome message in the GUI
            Label welcomeLabel = new Label("Hello! I'm Dookie.\n" +
                    "What can I do for you?");
            Image dookieImage = new Image(this.getClass().getResourceAsStream("/images/DaDookie.png"));
            fxmlLoader.<MainWindow>getController().getDialogContainer().getChildren().add(DialogBox.getDookieDialog(welcomeLabel.getText(), dookieImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
