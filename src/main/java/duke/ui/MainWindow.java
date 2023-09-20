package duke.ui;

import static duke.common.Messages.MESSAGE_FIRST_PROMPT;
import static duke.common.Messages.MESSAGE_WELCOME;

import duke.Duke;
import duke.storage.DukeStorageException;
import duke.storage.Storage;
import javafx.fxml.FXML;
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
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        showMessage(MESSAGE_WELCOME);
        showMessage("Is there any specific data file you would like to use? If none, just hit enter:");
    }

    /**
     * Shows a response to the user via the javafx GUI.
     *
     * @param message the message to display, line by line
     */
    private void showMessage(String... message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(String.join("\n", message), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] response = duke.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        showMessage(response);
        userInput.clear();
    }
}
