package ui;

import data.exception.DukeException;
import data.exception.InvalidDateParamException;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(d.startIntroduction(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = duke.getResponse(input);
            if (response.equals("")) {
                return;
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        } catch (DukeException | InvalidDateParamException e) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(e.toString(), dukeImage)
            );
            userInput.clear();
        }
    }
}
