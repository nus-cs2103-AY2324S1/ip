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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Penguin.png"));

    /**
     * Initializes the user interface components.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        duke = new Duke(); // Instantiate Duke here to initialize it
        String startMessage = duke.getResponse("start");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startMessage, dukeImage));
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

        // Automatically exits when input command is "bye"
        if (response.equals("Bye for now, hope to see you soon!")) {
            // Create a pause transition so user has time to read the response
            PauseTransition pause = new PauseTransition(Duration.seconds(1.25));
            pause.setOnFinished(event -> Platform.exit());

            pause.play();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }
}
