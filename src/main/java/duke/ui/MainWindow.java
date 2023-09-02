package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.fxml.FXML;
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

    // The Duke instance that is used by the GUI.
    private Duke duke;

    // Images to be used for the dialog boxes.
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance to be used by the GUI.
     *
     * @param d The Duke instance to be used by the GUI.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Greets the user.
     * Updates the dialog container to display Duke's greeting in a new dialog box.
     */
    public void greet() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.greet(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply.
     * Then, append both dialog boxes to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        // Makes the dialog boxes responsive to the size of the text.
        // This ensures that text is not clipped by the dialog boxes.
        // Solution inspired by https://stackoverflow.com/a/42601328
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();
    }
}
