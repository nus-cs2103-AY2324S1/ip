package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pooh.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/piglet.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Oink! I'm Botty!\nWhat can I do for you?", dukeImage)
        );
    }

    /**
     * Sets the duke and stage for the main window.
     *
     * @param d the duke
     * @param stage the stage
     */
    public void setDuke(Duke d, Stage stage) {
        this.stage = stage;
        this.duke = d;
        assert this.stage != null : "stage should not be null";
        assert this.duke != null : "duke should not be null";
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Returns the response from duke.
     *
     * @param input the user input.
     * @return the response from duke.
     */
    private String getResponse(String input) {
        return duke.reply(input, stage);
    }
}
