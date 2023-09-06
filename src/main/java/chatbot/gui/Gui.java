package chatbot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import chatbot.ChatBot;
import chatbot.exceptions.ChatBotException;

public class Gui {
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image chatBotImage = new Image(this.getClass().getResourceAsStream("/images/ChatBot.png"));
    @FXML
    private ScrollPane chatHistoryScrollPane;
    @FXML
    private VBox chatContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChatBot chatBot;

    @FXML
    public void initialize() {
        chatHistoryScrollPane.vvalueProperty().bind(chatContainer.heightProperty());
    }

    public void setChatBot(ChatBot chatBot) {
        this.chatBot = chatBot;
        addChatBotDialog(this.chatBot.greet());
        String initTaskListResponse = this.chatBot.initTaskList();
        if (initTaskListResponse != "ok") {
            addChatBotDialog(initTaskListResponse);
        };
    }

    private void addUserDialog(String text) {
        this.chatContainer.getChildren().add(
                DialogBox.getUserDialogBox(text, userImage)
        );
    }

    private void addChatBotDialog(String text) {
        this.chatContainer.getChildren().add(
                DialogBox.getChatBotDialogBox(text, chatBotImage)
        );
    }

    @FXML
    private void handleUserInput() {
        addUserDialog(this.userInput.getText());
        addChatBotDialog(getResponse(this.userInput.getText()));
        this.userInput.clear();
    }

    private String getResponse(String input) {
        try {
            return chatBot.handleCommand(input);
        } catch (ChatBotException e) {
            return e.toString();
        }

    }
}
