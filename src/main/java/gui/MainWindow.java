package gui;

import duke.Duke;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/you.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Yours.png"));

    /**
     * Initializes the main window and binds the scroll pane's vertical value to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        String welcomeMessage = "Hello! I'm YOURS. I miss you so much! How can I assist you today?";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance to be used for processing user input and generating responses.
     *
     * @param d The Duke instance to set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
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
        if (response.equals("Bye")) {
            handleExitCommand();
        }
    }

    private void handleExitCommand() {
        // Create a timeline that waits for 5 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            // Close the GUI window
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();

            // Exit the application
            Platform.exit();
        }));

        // Start the timeline
        timeline.play();
    }

}
