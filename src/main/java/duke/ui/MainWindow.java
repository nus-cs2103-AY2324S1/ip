package duke.ui;

import duke.Duke;
import duke.DukeException;
import duke.common.Message;
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke chatbot.
     * @param d
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Greets the user when the chatbot is launched.
     */
    public void greet() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Message.GREET, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        try {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.toString(), dukeImage)
            );
        }
        userInput.clear();
    }
}
