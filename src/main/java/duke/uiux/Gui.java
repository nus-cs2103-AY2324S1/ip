package duke.uiux;

import duke.ModelViewController;
import duke.Response;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

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
