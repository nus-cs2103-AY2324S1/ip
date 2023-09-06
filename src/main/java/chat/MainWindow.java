package chat;

import chat.controls.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("resources/images/linus.jpg"));
    private Image chatImage = new Image(this.getClass().getResourceAsStream("resources/images/defaultpepe.jpg"));

    /**
     * Initialize MainWindow and print starting message for user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getChatDialog("Welcome to chat. Please enter a command.", chatImage)
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
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChatDialog(response, chatImage)
            );
            userInput.clear();
        }
    }
}
