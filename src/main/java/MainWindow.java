import duke.Duke;
import duke.exceptions.InvalidFileTypeException;
import javafx.fxml.FXML;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke2.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        sendButton.setStyle("-fx-background-color: #52be80; -fx-background-radius:80");
        try {
            duke.load();
        } catch (InvalidFileTypeException e) {
            String errMessage = "Initialising storage file";
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(errMessage, dukeImage));
        }
        greetUser();
        displayCredits();
    }

    /**
     * Displays credits to third party resources used.
     */
    public void displayCredits() {
        String credits = "Display pictures taken from tumblr:\nhttps://jenni-illustrations.tumblr.com/\n"
                + "Ui design by @boonhaii:\nhttps://github.com/boonhaii/ip";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(credits, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            new Timer().schedule(
                    new TimerTask() {
                        public void run() {
                            Platform.exit();
                            System.exit(0); }
                    }, 2000);
        }
    }

    /**
     * Creates a dialog box to greet the User
     */
    @FXML
    private void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hi I am duke.Duke!", dukeImage)
        );
    }
}