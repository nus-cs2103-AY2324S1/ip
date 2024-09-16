package todoify.gui;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import todoify.chatbot.ChatMessage;
import todoify.chatbot.Chatbot;
import todoify.util.EpochConverter;

/**
 * The chat bubble's UI controller.
 */
public class ChatBubble extends HBox {

    private final Chatbot chatbot;
    private final ChatMessage message;

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

        this.initUI();
        this.refreshUI();
    }

    /**
     * Initializes the UI for this instance. Does any setup not doable in FXML.
     */
    private void initUI() {
        // Clip image to a circle.
        // Modified from https://stackoverflow.com/questions/50093207/javafx-circular-cutout
        double width = this.senderImageView.getFitWidth();
        double height = this.senderImageView.getFitHeight();
        Circle circle = new Circle(width / 2, height / 2, Math.min(width, height) / 2);
        this.senderImageView.setClip(circle);
    }

    /**
     * Reloads the UI according to the cached message instance.
     */
    public void refreshUI() {

        assert this.chatbot != null;
        assert this.message != null;

        assert this.messageLabel != null;
        assert this.timestampLabel != null;
        assert this.senderImageView != null;
        assert this.titleLabel != null;

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
