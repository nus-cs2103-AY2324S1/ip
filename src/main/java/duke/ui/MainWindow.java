package duke.ui;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import javafx.application.Platform;
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
     * Initialises the chat container, and displays greet message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DukeDialog.getDukeDialog("Hello! I'm TopG\n"
                        + "What can I do for you?\n"
                        + "Type 'help' to view available commands",
                        dukeImage)
        );
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialog.getUserDialog(input, userImage),
                DukeDialog.getDukeDialog(response, dukeImage)
        );

        // Closes the GUI
        if (input.toLowerCase().equals("bye")) {
            TimerTask exitTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            };
            Timer timer = new Timer("Delay");
            timer.schedule(exitTask, 1200L);
        }

        userInput.clear();
    }
}

