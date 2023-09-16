package dogebot;

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

    private DogeBot dogebot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_gigachad.png"));
    private Image dogebotImage = new Image(this.getClass().getResourceAsStream("/images/dogebot.png"));

    /**
     * Initialize GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDogeBot(DogeBot d) {
        dogebot = d;

        // UI intro message
        String introMessage = dogebot.ui.intro();
        dialogContainer.getChildren().addAll(DialogBox.getDogeBotDialog(introMessage, dogebotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dogebot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDogeBotDialog(response, dogebotImage)
        );
        userInput.clear();
    }
}
