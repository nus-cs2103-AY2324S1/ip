package duke.taskmanagement;

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

//Solution below adapted by https://se-education.org/guides/tutorials/javaFxPart4.html

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
     * Initializes the MainWindow. Binds the vertical scroll position of the scrollPane to the height
     * of the dialogContainer to ensure automatic scrolling as new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance associated with this MainWindow.
     *
     * @param d The Duke instance to set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Greets the user by displaying Duke's initial greeting message in the dialog container.
     */
    public void greetUser() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.greet(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        // @@author phiphi-tan-reused
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1)); // 1 second delay
            pause.setOnFinished(event -> {
                // Close the JavaFX application after the delay
                Platform.exit();
            });
            pause.play();
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

    }
}