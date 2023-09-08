package duke.gui;

import duke.Duke;
import duke.exception.DukeException;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm ForsakenX\nWhat can I do for you?", dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * @throws DukeException if any error occurs.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response;
        try {
            response = duke.getResponseToGui(input);
        } catch (DukeException e) {
            // An exception occurs, to replace response with exception message.
            response = e.getMessage().replace("☹", ":P"); // JavaFX cannot recognize "☹"
        }
        assert (response != null) : "Response is empty";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        // If "bye" command, disable user from continue typing and sending commands.
        if (input.strip().equalsIgnoreCase("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }

        // Clears user input.
        userInput.clear();
    }
}
