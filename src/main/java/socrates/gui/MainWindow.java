package socrates.gui;

import static socrates.Socrates.WELCOME_MESSAGE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import socrates.Socrates;
import socrates.command.CommandResult;
import socrates.data.exception.SocratesException;

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

    private Socrates socrates;

    /**
     * Initializes the main window with the given welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addSocratesDialog(WELCOME_MESSAGE);
    }

    public void setSocrates(Socrates d) {
        socrates = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Socrates's reply and then
     * appends them to the dialog container. Clears the user input after processing.
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
            CommandResult commandResult = socrates.getResponse(input);
            addSocratesDialog(commandResult.getResponseToUser());

            if (commandResult.isExit()) {
                handleExit();
            }
        } catch (SocratesException e) {
            addSocratesDialog(e.getMessage());
        }
    }

    private void addUserDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message));
    }

    private void addSocratesDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getSocratesDialog(message));
    }

    private void handleExit() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}
