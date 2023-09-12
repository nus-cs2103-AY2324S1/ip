package todoify.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import todoify.Main;
import todoify.chatbot.ChatMessage;
import todoify.chatbot.Chatbot;
import todoify.util.events.EventListener;

/**
 * The main window's UI controller.
 */
public class MainWindowController extends Application {

    private Chatbot chatbot = null;
    private final EventListener<ChatMessage> chatMessageEventListener = this::appendMessage;


    @FXML
    private Button sendMessageButton;
    @FXML
    private TextField userMessageTextField;
    @FXML
    private ScrollPane messageHistoryScrollPane;
    @FXML
    private VBox messageHistoryVerticalBox;

    /**
     * Initializes elements after FXML elements are loaded.
     */
    @FXML
    private void initialize() {
        assert this.messageHistoryVerticalBox != null;
        assert this.messageHistoryScrollPane != null;

        this.messageHistoryVerticalBox.heightProperty()
                .addListener(observable -> this.messageHistoryScrollPane.setVvalue(1D));
    }

    /**
     * Sets the chatbot used in the GUI to the given instance.
     *
     * @param chatbot The chatbot to use.
     */
    public void setChatbot(Chatbot chatbot) {
        if (this.chatbot != null) {
            this.chatbot.removeEventListener(this.chatMessageEventListener);
        }

        this.chatbot = chatbot;

        chatbot.openConversation();
        chatbot.addEventListener(this.chatMessageEventListener);
        this.reloadMessageHistory();
    }


    /**
     * Consumes the user input from the text field and clears it from the GUI.
     */
    @FXML
    private void handleUserInput() {
        assert this.chatbot != null;
        assert this.userMessageTextField != null;

        String input = this.userMessageTextField.getText().trim();
        if (!input.isBlank()) {
            this.chatbot.sendMessageFromUser(input);
        }

        this.userMessageTextField.clear();

    }

    /**
     * Handles the key event in a textbox.
     */
    @FXML
    private void handleKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            this.handleUserInput();
        }
    }

    /**
     * Appends the given message onto the end of the message history on the GUI.
     *
     * @param message The message to draw.
     */
    private void appendMessage(ChatMessage message) {
        assert this.messageHistoryVerticalBox != null;
        assert this.messageHistoryScrollPane != null;

        this.messageHistoryVerticalBox.getChildren().add(new ChatBubble(this.chatbot, message));

        this.messageHistoryScrollPane.layout();
        this.messageHistoryVerticalBox.layout();
        this.messageHistoryScrollPane.setVvalue(1.0);

        this.refreshUiState();
    }

    /**
     * Re-renders the message history on the GUI, by clearing all elements and adding whatever that's present in the
     * chatbot into the GUI.
     */
    private void reloadMessageHistory() {
        assert this.messageHistoryVerticalBox != null;

        this.messageHistoryVerticalBox.getChildren().clear();

        if (this.chatbot == null) {
            return;
        }

        for (ChatMessage message : this.chatbot.getConversation()) {
            this.appendMessage(message);
        }
    }

    /**
     * Updates the GUI state to match that of the chatbot. For instance, disable the buttons if the chatbot conversation
     * is closed.
     */
    private void refreshUiState() {
        assert this.userMessageTextField != null;
        assert this.sendMessageButton != null;
        assert this.chatbot != null;

        this.userMessageTextField.setDisable(this.chatbot.isConversationClosed());
        this.sendMessageButton.setDisable(this.chatbot.isConversationClosed());
    }

    @Override
    public void start(Stage stage) throws Exception {
        Chatbot bot = new Chatbot(null, null);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(bot.getName());
            stage.setMinWidth(320);
            stage.setMinHeight(320);
            fxmlLoader.<MainWindowController>getController().setChatbot(bot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
