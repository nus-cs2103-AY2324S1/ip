package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The `MainWindow` class serves as the controller for the main window of the Duke GUI.
 * It provides the layout and functionality for the user interface components, such as the
 * input field, message display area, and send button.
 */
public class MainWindow extends AnchorPane {

    /** The scroll pane component for displaying the chat messages and enabling scrolling. */
    @FXML
    private ScrollPane scrollPane;

    /** The container for displaying dialog boxes containing user and Duke messages. */
    @FXML
    private VBox dialogContainer;

    /** The input field where the user enters text messages. */
    @FXML
    private TextField userInput;

    /** The button for sending user messages or interacting with the application. */
    @FXML
    private Button sendButton;

    /** The Duke instance that handles the application's logic. */
    private Duke duke;

    /** The user's avatar image. */
    private Image userImage;

    /** Duke's avatar image. */
    private Image dukeImage;

    /**
     * Initializes the `MainWindow` controller after the FXML has been loaded.
     * It binds the scroll pane's vertical value to the dialog container's height,
     * ensuring automatic scrolling as messages are added.
     */
    @FXML
    public void initialize() {
        // Add assertions to check assumptions about UI components
        assert scrollPane != null : "ScrollPane should not be null";
        assert dialogContainer != null : "DialogContainer should not be null";
        assert userInput != null : "UserInput should not be null";
        assert sendButton != null : "SendButton should not be null";

        String dukeImagePath = "/images/DaDuke.png";
        String userImagePath = "/images/DaUser.png";

        // Use assertions to check for valid image paths
        assert getClass().getResource(dukeImagePath) != null : "Duke image path is invalid: " + dukeImagePath;
        assert getClass().getResource(userImagePath) != null : "User image path is invalid: " + userImagePath;

        // Load the images
        userImage = new Image(getClass().getResourceAsStream(userImagePath));
        dukeImage = new Image(getClass().getResourceAsStream(dukeImagePath));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for the controller to enable communication with the Duke logic.
     *
     * @param d The Duke instance that handles the application's logic.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.initialise(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
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


