package Duke;

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

    private boolean hasEnded;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the MainWindow controller.
     * Binds the scroll pane's vertical value property to the dialog container's height property.
     * Initializes the user interface and sets the initial state.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ui = new Ui();
        hasEnded = false;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.getWelcome(), dukeImage)
        );
        assert dialogContainer.getChildren().size() == 1;
        dialogContainer.autosize();
    }

    /**
     * Sets the Duke object for this controller.
     *
     * @param d The Duke chatbot.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles user input by processing it with the Duke chatbot.
     * Creates dialog boxes for user input and Duke's response, appends them to the dialog container,
     * and checks if the chatbot has ended.
     */
    @FXML
    private void handleUserInput() {
        if (hasEnded) {
            userInput.clear();
            return;
        }
        String input = userInput.getText();
        userInput.clear();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        hasEnded = duke.hasEnded();
    }
}
