package chatbot.ui;

import java.util.Timer;
import java.util.TimerTask;

import chatbot.Chatbot;
import javafx.application.Platform;
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

    private Chatbot chatbot;

    private final Image userImage = new Image((this.getClass().getResourceAsStream("/images/userkhaled.jpeg")));
    private final Image chatbotImage = new Image((this.getClass().getResourceAsStream("/images/chatbotkhaled.jpeg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChatbot(Chatbot d) {
        chatbot = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getChatbotDialog(chatbot.getGreeting(), chatbotImage));
    }

    /**
     * Exits the chatbot.
     */
    public void exit() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 1500);
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChatbotDialog(response, chatbotImage)
        );
        userInput.clear();
        
        if (input.equals("bye")) {
            this.exit();
        }
    }
}
