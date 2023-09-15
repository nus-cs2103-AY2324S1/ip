package ui;

import duke.Duke;
import duke.DukeException;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/blue.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/red.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(new DialogBox("Hello! I'm Red\nWhat can I do for you?",
                dukeImage, 1));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    public String getUserInput() {
        return userInput.getText();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            runUserInput();
        } catch (DukeException e) {
            errMessageResponse(e);
        }
    }

    private void runUserInput() {
        String input = getUserInput();
        String response = duke.response(input);
        dialogContainer.getChildren().addAll(
                new DialogBox(input, userImage, 0),
                new DialogBox(response, dukeImage, 1)
        );
        userInput.clear();
    }

    private void errMessageResponse(DukeException e) {
        dialogContainer.getChildren().addAll(
                new DialogBox(userInput.getText(), userImage, 0),
                new DialogBox(e.getMessage(), dukeImage, 1)
        );
        userInput.clear();
    }
}
