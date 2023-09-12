package chatbot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class ChatbotController {
    @FXML
    private TextArea displayArea;
    @FXML
    private TextField inputField;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;

    private Image userImage;
    private Image botImage;
    private Chatbot chatbot;

    public void initialize() {
        botImage = loadImage("/DaDuke.png");
        userImage = loadImage("/DaUser.png");

        assert botImage != null : "Bot image cannot be empty.";
        assert userImage != null : "User image cannot be empty.";
        assert dialogContainer != null : "dialogContainer should be initialised.";

        Label greetLabel = new Label("Hi, I am Duke. What can I do for you?");
        DialogBox greetingDialog = DialogBox.setDukeDialog(greetLabel, new ImageView(botImage));
        dialogContainer.getChildren().add(greetingDialog);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    public void setChatbot(Chatbot chatbot) {
        this.chatbot = chatbot;
    }


    private Image loadImage(String path) {
        InputStream imageStream = this.getClass().getResourceAsStream(path);
        if (imageStream == null) {
            System.err.println("Failed to load image from path: " + path);
            throw new RuntimeException("Image not found: " + path);
        }
        return new Image(imageStream);
    }

    public void showError(String message) {
        displayArea.appendText("Error: " + message + "\n");
    }

    @FXML
    private void handleSendButtonClick(ActionEvent event) {
        String userInput = inputField.getText();

        if (!userInput.isEmpty()) {
            // Display user input
            dialogContainer.getChildren().add(DialogBox.setUserDialog(new Label(userInput),new ImageView(userImage)));

            // Check if chatbot is not null before getting a response
            if (chatbot != null) {
                String botResponse = chatbot.processUserInput(userInput);
                dialogContainer.getChildren().add(DialogBox.setDukeDialog(new Label(botResponse),new ImageView(botImage)));
            } else {
                dialogContainer.getChildren().add(DialogBox.setDukeDialog(new Label(userInput),new ImageView(botImage)));
            }

            inputField.clear();
        }
    }

}

