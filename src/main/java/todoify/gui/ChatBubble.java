package todoify.gui;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import todoify.chatbot.ChatMessage;
import todoify.chatbot.Chatbot;
import todoify.util.EpochConverter;

/**
 * The chat bubble's UI controller.
 */
public class ChatBubble extends HBox {

    private Chatbot chatbot;
    private ChatMessage message;

    @FXML
    private ImageView senderImageView;
    @FXML
    private Label messageLabel;
    @FXML
    private Label timestampLabel;
    @FXML
    private Label titleLabel;


    /**
     * Constructs a chat bubble UI element using the given chat message.
     *
     * @param message The message to construct from.
     */
    public ChatBubble(Chatbot chatbot, ChatMessage message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindowController.class.getResource("/view/ChatBubble.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.chatbot = chatbot;
        this.message = message;
        this.refreshUI();
    }


    /**
     * Reloads the UI according to the cached message instance.
     */
    public void refreshUI() {

        Image image = null;
        String title = "?";
        switch (this.message.getSenderType()) {
        case USER:
            image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.jpg")));
            title = "You";
            break;
        case CHATBOT:
            image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/chatbot.jpg")));
            title = this.chatbot.getName();
            break;
        default:
            break;
        }

        this.messageLabel.setText(this.message.getMessage());
        this.timestampLabel.setText(EpochConverter.getUserReadableTimeStringFromEpoch(this.message.getTimestamp()));
        this.senderImageView.setImage(image);
        this.titleLabel.setText(title);

    }

}
