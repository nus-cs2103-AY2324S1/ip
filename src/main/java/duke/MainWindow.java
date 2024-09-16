package duke;

import javafx.application.Platform;
import javafx.concurrent.Task;
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
     * Initializes the MainWindow, binds the scrollPane to the dialogContainer's height, and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Luke. Type any command to start", dukeImage)
        );
    }

    /**
     * Sets the Duke instance to be used by the MainWindow.
     *
     * @param d The Duke instance
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles user input, displays it in a dialog box, gets Duke's response, and displays Duke's response in a
     * dialog box.
     * Clears the user input field after processing. Exits the application if the user input is "bye".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(duke.bye(), dukeImage)
            );
            userInput.clear();
            duke.saveData();
            delay(2000, Platform::exit);
            return;
        }

        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    //@@author HEEaZ-reused
    // Reused from https://stackoverflow.com/questions/26454149
    /**
     * Delays the execution of the continuation Runnable for a specified number of milliseconds.
     *
     * @param millis       The delay duration in milliseconds
     * @param continuation The Runnable to execute after the delay
     */
    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    // Ignore exception
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }
    //@@author
}
