package app;

import duke.Duke;
import duke.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.Ui;

/**
 * The `MainWindow` class represents the main graphical user interface (GUI) window for
 * interacting with the Duke chatbot.
 * It includes input fields, a conversation display area, and methods for handling user input and displaying responses.
 *
 * This class is designed to work with JavaFX and is part of a Duke chatbot application.
 *
 * @author raydenlim
 * @version 0.0.0
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
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/maid.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/whale bloop bloop.png"));

    /**
     * Initializes the main window, binding the scroll pane's vertical value to the dialog container's height property.
     * This ensures that the conversation always scrolls to the latest message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke chatbot instance that this `MainWindow` interacts with.
     *
     * @param d The Duke chatbot instance to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles user input by processing it with the Duke chatbot, displaying the user's input and Duke's response
     * in the conversation container, and clearing the user input field.
     *
     * @throws DukeException If there is an issue processing the user input by Duke.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Displays the welcome message from the Duke chatbot in the conversation container.
     */
    public void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.showWelcome(), dukeImage));
    }
}
