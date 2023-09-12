package simon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow of the Simon application.
 * Provides the layout for the other controls and manages user interactions.
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

    /** The main Simon application instance. */
    private Simon simon;

    /** Image representing the user in the dialog box. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** Image representing Duke (Simon) in the dialog box. */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the UI components and displays the welcome message.
     */
    @FXML
    public void initialize() {
        Ui ui = new Ui();
        Ui.showWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.getOutput(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the main Simon instance to this controller.
     *
     * @param d The Simon instance to be set.
     */
    public void setDuke(Simon d) {
        simon = d;
    }

    /**
     * Handles the user's input when a message is sent.
     * It creates two dialog boxes - one for the user's message and the other for Simon's reply.
     * The user's input is then cleared.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = simon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
