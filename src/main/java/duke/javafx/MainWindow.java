package duke.javafx;

import duke.Duke;
import duke.Ui;
import javafx.application.Platform;
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
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/frog.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/memeMan.png"));

    /**
     * Initializes the MainWindow by binding the scrollPane's vertical value to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.showWelcome(), dukeImage)
        );
    }

    /**
     * Sets the Duke instance for this MainWindow.
     *
     * @param d The Duke instance to set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles user input by processing it with Duke and displaying the conversation in dialog boxes.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (response.equals(ui.showBye())) {
            Platform.exit();
        }
        userInput.clear();
    }
}
