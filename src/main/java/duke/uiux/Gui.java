package duke.uiux;

import duke.Duke;
import duke.ModelViewController;
import duke.Response;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));
    @Override
    public void start(Stage stage) {
        // All UI information stored in MainWindow.fxml and DialogBox.fxml, no need for start method
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ModelViewController mvc = new ModelViewController();
        Response reply = mvc.getResponse(input);
        return reply.getResponse();
    }
}
