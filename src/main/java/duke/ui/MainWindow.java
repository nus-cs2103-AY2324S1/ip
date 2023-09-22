package duke.ui;

import duke.Duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private static final Image userImage = new Image(UserDialogBox.class.getResourceAsStream("/images/User.jpg"));
    private static final Image dukeImage = new Image(DukeDialogBox.class.getResourceAsStream("/images/Duke.jpg"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Add assertions
        assert scrollPane != null : "ScrollPane should not be null";
        assert dialogContainer != null : "VBox dialogContainer should not be null";
        assert userInput != null : "TextField userInput should not be null";

    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        assert duke != null : "Duke instance should not be null";
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
                UserDialogBox.getUserDialog(input, userImage),
                DukeDialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
        userInput.clear();
    }
}
