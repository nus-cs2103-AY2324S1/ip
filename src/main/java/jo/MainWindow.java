package jo;

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

    private Jo jo;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image joImage = new Image(this.getClass().getResourceAsStream("/images/JoImage.png"));

    /**
     * Initializes the MainWindow and adds a greeting message by the bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = "Hello! I am Jo.\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getJoDialog(greetingMessage, joImage)
        );
    }

    public void setJo(Jo j) {
        this.jo = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws JoException {
        String input = userInput.getText();
        String response = jo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJoDialog(response, joImage)
        );
        userInput.clear();
    }
}

