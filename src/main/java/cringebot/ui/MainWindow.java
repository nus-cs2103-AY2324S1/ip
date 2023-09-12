package cringebot.ui;

import cringebot.CringeBot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Main window for the chat interface
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

    private CringeBot cringeBot;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/ChadUser.jpeg")));
    private final Image cringeBotImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/CringeBot.jpeg")));

    /**
     * Initialises the dialog containers and greetings
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getCringeBotDialog(Ui.greetUser(), cringeBotImage)
        );
    }

    /**
     * Sets the chatbot to be used throughout
     *
     * @param d chatbot to be used
     */
    public void setCringeBot(CringeBot d) {
        this.cringeBot = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing CringeBot reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cringeBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCringeBotDialog(response, cringeBotImage)
        );
        userInput.clear();
    }
}
