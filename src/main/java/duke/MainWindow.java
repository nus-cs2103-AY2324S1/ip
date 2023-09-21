package duke;

import static duke.Ui.showWelcome;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the MainWindow of the Duke chatbot application.
 * Provides the layout for the user input and chat dialogs and handles user interactions.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/max.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lewis.jpeg"));

    /**
     * Initializes the main chat window.
     * Sets the welcome message if Duke has been initialized.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        if (duke != null) { // Ensure duke is initialized.
            String welcomeMessage = showWelcome();
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
        }
    }


    /**
     * Assigns a Duke instance to this controller.
     *
     * @param d The Duke instance to be used.
     */
    public void setDuke(Duke d) {
        duke = d;
        postInitialize();
        dialogContainer.autosize();
    }

    /**
     * Displays Duke's welcome message post initialization.
     */
    private void postInitialize() {
        String welcomeMessage = Ui.showWelcome();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
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
    }
}
