package chatbot.ui;

import chatbot.ChatBot;
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

    private ChatBot chatBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bobby Wasabi.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChatBot(ChatBot c) {
        chatBot = c;
        startChat();
    }

    @FXML
    private void startChat() {
        String response = "Hello I am Bobby Wasabi, what can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getChatBotDialog(response, botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response =chatBot.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChatBotDialog(response, botImage)
        );
        userInput.clear();
    }
}

