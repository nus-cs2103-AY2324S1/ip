package chat;

import chat.controls.DialogBox;
import chat.utils.Ui.ChatWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Main Window Controller for Chat.
 * @author juzzztinsoong
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

    private Chat chat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/linus.jpg"));
    private Image chatImage = new Image(this.getClass().getResourceAsStream("/images/defaultpepe.jpg"));

    /**
     * Initialize MainWindow and print starting message for user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getChatDialog(new ChatWrapper("Chat: ", chatImage, Color.LIMEGREEN), "What's up gamers! Enter a command to get a response from your favourite streamers. Also, type 'meme' for something funny.")
        );
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (!chat.hasExited()) {
            String input = userInput.getText();
            String response = chat.getResponse(input);
            ChatWrapper chatWrapper = chat.getChatWrapper();
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            dialogContainer.getChildren().add(DialogBox.getChatDialog(chatWrapper, response));
            userInput.clear();
        }
    }
}
