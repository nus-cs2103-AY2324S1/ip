
import common.Message;
import exceptions.ThorndikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main graphical user interface window of the application.
 * It includes a chat dialog interface where the user can interact with the
 * Thorndike chatbot.
 * 
 * @author Ho Khee Wei
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

    private Thorndike thorndike;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window and binds the scroll pane to the height of the
     * dialog container for automatic scrolling when new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Thorndike chatbot instance that will be used for responding to user
     * input.
     *
     * @param d The Thorndike chatbot instance.
     */
    public void setDuke(Thorndike d) {
        thorndike = d;
    }

    /**
     * Displays the initial greeting message from Thorndike when the application
     * starts.
     */
    public void greet() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Message.GREET, dukeImage));
    }

    /**
     * Handles user input by adding the user's message to the dialog container,
     * sending it to Thorndike
     * for a response, and displaying Thorndike's response or an error message if an
     * exception occurs.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage));
        try {
            String response = thorndike.getResponse(input);
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(response, dukeImage));
        } catch (ThorndikeException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }

        userInput.clear();
    }
}
