package duke.gui;

import duke.Duke;
import duke.DukeException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String WELCOME_MSG = "Hi! This is your intelligent friend L.\n\"Dream big.\"\n"
            + "What can I do for you today?";
    private static final String BYE_MSG = "Bye!\n\"Beware the barrenness of a busy life.\"";

    private static final String ERROR_MSG = "⚠ Oops! Something wrong when reading the data:(";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        try {
            this.duke = new Duke();
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(WELCOME_MSG, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(ERROR_MSG, dukeImage)
            );
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
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
        if (response.equals(BYE_MSG)) {
            // Close the window after 5 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }
}

