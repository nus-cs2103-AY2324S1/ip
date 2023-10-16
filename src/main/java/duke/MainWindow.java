package duke;

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

    // Images for the user and Duke
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image captainImage = new Image(this.getClass().getResourceAsStream("/images/DaCaptain.jpg"));

    /**
     * Initializes the main window by binding the scroll pane's vertical value to
     * the dialog container's height property.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for this main window and initializes the greeting message from Duke.
     *
     * @param d The Duke instance to set.
     */
    public void setDuke(Duke d) {
        duke = d;

        //Initialize Greeting from Bot
        String input = Command.START;
        String response = duke.generateResponse(input);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, captainImage));
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.generateResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, captainImage)
        );
        userInput.clear();
    }
}
