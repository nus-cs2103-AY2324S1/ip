package sally;

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

    private Sally sally;

    public MainWindow() {
    }

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image sallyImage = new Image(this.getClass().getResourceAsStream("/images/DaSally.png"));

    /**
     * Initializes the first message sent by Sally to welcome the user
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String hello = "Hello! It's Sally here!\nHow can I help you today?";
        dialogContainer.getChildren().add(
                DialogBox.getSallyDialog(hello, sallyImage)
        );
    }

    /**
     * Sets the Sally object for the MainWindow
     *
     * @param s Sets the Sally object
     */
    public void setSally(Sally s) {
        sally = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sally's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSallyDialog(response, sallyImage)
        );
        userInput.clear();
    }

    /**
     * Gets the response from Sally
     *
     * @param input User input
     * @return A string response from Sally
     */
    private String getResponse(String input) {
        return sally.execute(input);
    }
}
