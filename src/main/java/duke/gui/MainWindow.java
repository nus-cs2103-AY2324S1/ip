package duke.gui;

import static duke.Duke.WELCOME_MESSAGE;

import duke.Duke;
import duke.command.CommandResult;
import duke.data.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the main window with the given welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addDukeDialog(WELCOME_MESSAGE);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }
        userInput.clear();
        addUserDialog(input);

        try {
            CommandResult commandResult = duke.getResponse(input);
            addDukeDialog(commandResult.getResponseToUser());

            if (commandResult.isExit()) {
                handleExit();
            }
        } catch (DukeException e) {
            addDukeDialog(e.getMessage());
        }
    }

    private void addUserDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message));
    }

    private void addDukeDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message));
    }

    private void handleExit() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}
