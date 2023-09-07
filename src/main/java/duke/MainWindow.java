package duke;

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
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Welcome Messi! How can I help you?", dukeImage)
                );
        dialogContainer.autosize();
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(input.equals("bye") ? "Goodbye! Closing the chat." : response, dukeImage)
        );
        userInput.clear();

        if (response.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }


    }

    @FXML
    public void greetUser() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello Messi!"
                        + "Welcome to Ronaldo's world!"
                        + "The following are the commands that I offer:\n"
                        + "list\n"
                        + "mark\n"
                        + "unmark\n"
                        + "todo\n"
                        + "deadline\n"
                        + "event\n"
                        + "find\n"
                        + "bye\n", dukeImage)
        );
    }
}

