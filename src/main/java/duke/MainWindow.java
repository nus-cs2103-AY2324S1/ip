package duke;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import duke.ui.Ui;
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

    private Duke ding;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dauser.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/daduke.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke chatbot for this GUI.
     *
     * @param d The Duke chatbot.
     */
    public void setDuke(Duke d) {
        ding = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ding's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ding.getResponse(input.toString());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Displays a welcome message in the dialog container when the application starts.
     */
    public void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.introReply(), dukeImage));
    }
}
