package dude;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author xenosf-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// With minor alterations

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dudeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Dude dude;

    @FXML
    public void initialize() {
        assert userImage != null : "User display image should not be null.";
        assert dudeImage != null : "Dude display image should not be null.";

        // enable/disable button based on whether input is blank
        updateButtonStatus();
        userInput.textProperty().addListener((obs, oldText, newText) -> {
            updateButtonStatus();
        });

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDude(Dude d) {
        dude = d;
        String firstMsg = dude.initialize();
        assert dude != null : "Dude instance should not be null.";
        dialogContainer.getChildren().addAll(
                DialogBox.getDudeDialog(firstMsg, dudeImage)
        );
    }

    //@@author

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dude's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // read user input
        String input = userInput.getText();

        // ignore empty user input
        if (input.isBlank()) {
            return;
        }

        // handle user input
        String response = dude.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDudeDialog(response, dudeImage)
        );
        userInput.clear();
    }

    /**
     * Enables/disables send button according to input.
     */
    private void updateButtonStatus() {
        // read user input
        String input = userInput.getText();

        // disable send button if user input is empty
        sendButton.setDisable(input.isBlank());
    }
}
