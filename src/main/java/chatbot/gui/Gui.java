package chatbot.gui;

import chatbot.ChatBot;
import chatbot.exceptions.ChatBotException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

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
        addChatBotDialog(this.chatBot.greet(), false);
        String initTaskListResponse = this.chatBot.initTaskList();
        if (initTaskListResponse != ChatBot.INIT_TASKLIST_SUCCESS_STRING) {
            addChatBotDialog(initTaskListResponse, true);
        }
    }

    private void addUserDialog(String text) {
        assert (!text.isEmpty());
        this.chatContainer.getChildren().add(
                DialogBox.getUserDialogBox(text, userImage)
        );
    }

    private void addChatBotDialog(String text, boolean isErrorMessage) {
        assert (!text.isEmpty());
        this.chatContainer.getChildren().add(
                DialogBox.getChatBotDialogBox(text, chatBotImage, isErrorMessage)
        );
    }

    @FXML
    private void handleUserInput() {
        if (this.userInput.getText().isEmpty()) {
            return;
        }
        addUserDialog(this.userInput.getText());
        getResponse(this.userInput.getText());
        this.userInput.clear();
    }

    private void getResponse(String input) {
        assert (!input.isEmpty());
        try {
            addChatBotDialog(chatBot.handleCommand(input), false);
        } catch (ChatBotException e) {
            addChatBotDialog(e.toString(), true);
        }

    }
}
