package CR7;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Controller for CR7.MainWindow. Provides the layout for the other controls.
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

    private CR7 cr7;

    private String userImagePath = "/images/dillonbrooks.jpg";

    private String dukeImagePath = "/images/ronaldosiu.jpg";

    private Image userImage = new Image(this.getClass().getResourceAsStream(userImagePath));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(dukeImagePath));
    private boolean isWelcomeMessageSent = false; // Flag to track if welcome message has been sent

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendWelcomeMessage();
    }

    public void setDuke(CR7 d) {
        cr7 = d;
    }

    /**
     * Creates and sends the welcome message if it hasn't been sent yet.
     */
    private void sendWelcomeMessage() {
        if (!isWelcomeMessageSent) {
            String welcomeMsg = "Welcome to the chat! Type 'bye' to exit.\n";
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(welcomeMsg, dukeImage)
            );
            isWelcomeMessageSent = true; // Set the flag to true
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            String exitMsg = "Bye! Hope to see you again soon! SIUUUU\n";
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(exitMsg, dukeImage)
            );
            // Create a PauseTransition to delay the application exit
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // Adjust the duration as needed
            delay.setOnFinished(event -> {
                Platform.exit(); // Exit the application after the delay
            });
            delay.play();
        }
        String response = cr7.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
