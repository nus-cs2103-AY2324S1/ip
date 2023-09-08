package userstuff;

import java.util.concurrent.TimeUnit;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * A class to handle the Ui of chatbot.
 */
public class MainWindow extends AnchorPane {

    private static String bye = "Bye. Hope to see you again soon.";
    private static String greet = "Hi! I am Vishnu.\n How can I help you?\n";

    private static String response;


    private Duke duke;




    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the first dialog box in MainWindow.
     *
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greet, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        this.duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals(bye)) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Platform.exit();
            }
            Platform.exit();
        }
    }

    public void setMessage(String response) {
        MainWindow.response = response;
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public void setGreet(String greet) {
        MainWindow.greet = MainWindow.greet + greet;
    }
}


