package duke;

import java.util.Timer;
import java.util.TimerTask;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ken.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/barbie.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
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

        if (response.trim().isBlank()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );
            userInput.clear();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.exit();
                }
            };
            Timer timer = new Timer("Delay");
            timer.schedule(task, 1000L);
        }
    }

    @FXML
    private void showWelcomeMessage() {
        StringBuilder result = new StringBuilder();
        result.append("________________________________________________________");
        result.append("\n");
        result.append("Hello! I'm " + "ken" + "!");
        result.append("\n");
        result.append("What can I do for you?");
        result.append("\n");
        result.append("________________________________________________________");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(result.toString(), dukeImage)
        );
    }
}