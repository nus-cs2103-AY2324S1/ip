package duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import duke.Duke;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    Ui ui = new Ui();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image kronosImage = new Image(this.getClass().getResourceAsStream("/images/kronos.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
        showStartMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialogue boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialogue container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogueContainer.getChildren().addAll(
                DialogueBox.getUserDialogue(input, userImage),
                DialogueBox.getKronosDialogue(response, kronosImage));

        userInput.clear();
    }

    @FXML
    private void showStartMessage() {
        dialogueContainer.getChildren().addAll(
                DialogueBox.getKronosDialogue(
                        ui.showStartMessage(),
                        kronosImage));
    }
}