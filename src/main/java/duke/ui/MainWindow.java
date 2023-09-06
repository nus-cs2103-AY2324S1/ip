package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    public void setDuke(Duke d) {
        duke = d;
    }

    public static String combineStrings(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : array) {
            stringBuilder.append(str).append("\n");
        }

        // Remove the trailing newline character, if any
        int length = stringBuilder.length();
        if (length > 0 && stringBuilder.charAt(length - 1) == '\n') {
            stringBuilder.deleteCharAt(length - 1);
        }

        return stringBuilder.toString();
    }

    public void greet() {
        this.showDukeDialog(duke.greet());
    }

    public void showDukeDialog(String[] input) {
        this.showDukeDialog(combineStrings(input));
    }

    public void showDukeDialog(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(input, dukeImage));
    }

    public void showUserDialog(String[] input) {
        this.showUserDialog(combineStrings(input));
    }

    public void showUserDialog(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(input, userImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog);
        userInput.clear();
    }
}
