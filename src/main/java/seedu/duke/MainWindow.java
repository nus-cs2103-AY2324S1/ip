package seedu.duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() { // This method is called whenever a query is sent.
        String input = userInput.getText();
        // Get response based on Duke
        String response = duke.getResponse(input);
        appendDialog(input, response);
        userInput.clear();

        exitIfBye(input);
    }

    /**
     * Creates a DialogBox for the user and Duke and appends them to the dialogContainer.
     *
     * @param userText The input from the user.
     * @param dukeResponse The response from Duke
     */
    private void appendDialog(String userText, String dukeResponse) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeResponse, dukeImage)
        );
    }

    /**
     * Executes the exit animation if the user input is "bye".
     *
     * @param input The user input
     */
    private void exitIfBye(String input) {
        // Check if the user input is "bye"
        if (input.equalsIgnoreCase("bye")) {
            // Create a PauseTransition to delay the exit
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // Delay for 0.5 seconds

            // Set the action to be performed after the delay
            pause.setOnFinished(event -> {
                Platform.exit(); // Exit the application after the delay
            });

            // Start the pause transition
            pause.play();
        }
    }
}