package duke.utils;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Session class represents a user session in the Duke application.
 * It manages user input and responses during the session.
 */
public class Session extends Application {
    private static final String IMG_DIR = "file:img/";
    private static final String CHATBOT_AVATAR = IMG_DIR + "pb.jpg";
    private static final String USER_AVATAR = IMG_DIR + "rs.jpg";

    private Response response;
    private Input input;
    private VBox chatBox;
    private TextField inputField;

    /**
     * The main method to launch the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initializeUI(stage);
        this.input = new Input();
    }

    /**
     * Initializes the user interface for the Duke application.
     *
     * @param primaryStage The primary stage for the application.
     */
    public void initializeUI(Stage primaryStage) {
        BorderPane root = createRootPane();
        Scene scene = new Scene(root, 500, 500);
        
        setupPrimaryStage(primaryStage, scene);
        setupInitialGreeting();
    }

    /**
     * Creates the root BorderPane for the UI.
     *
     * @return The root BorderPane.
     */
    private BorderPane createRootPane() {
        BorderPane root = new BorderPane();
        
        chatBox = createChatBox();
        ScrollPane scrollPane = createScrollPane();
        inputField = createInputField();
        Button sendButton = createSendButton();
        HBox inputBox = createInputBox(sendButton);

        root.setCenter(scrollPane);
        root.setBottom(inputBox);
        
        return root;
    }

    /**
     * Creates the chat box VBox.
     *
     * @return The chat box VBox.
     */
    private VBox createChatBox() {
        VBox chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        return chatBox;
    }

    /**
     * Creates the scroll pane for the chat box.
     *
     * @return The scroll pane.
     */
    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

    /**
     * Creates the input field for user commands.
     *
     * @return The input field.
     */
    private TextField createInputField() {
        TextField inputField = new TextField();
        inputField.setPromptText("Enter your command...");
        return inputField;
    }

    /**
     * Creates the "Send" button.
     *
     * @return The "Send" button.
     */
    private Button createSendButton() {
        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> processUserInput());
        return sendButton;
    }

    /**
     * Creates the input box containing user image, input field, and "Send" button.
     *
     * @param sendButton The "Send" button.
     * @return The input box.
     */
    private HBox createInputBox(Button sendButton) {
        ImageView userImageView = getAvatarImageView(USER_AVATAR);
        HBox inputBox = new HBox(10);
        inputBox.setPadding(new Insets(10));
        inputBox.getChildren().addAll(userImageView, inputField, sendButton);
        return inputBox;
    }

    /**
     * Sets up the primary stage for the application.
     *
     * @param primaryStage The primary stage.
     * @param scene The scene to be displayed.
     */
    private void setupPrimaryStage(Stage primaryStage, Scene scene) {
        primaryStage.setTitle("Duke Chatbot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Displays the initial greeting in the chatbox.
     */
    private void setupInitialGreeting() {
        appendMessage(Response.GREETINGS.toString(), CHATBOT_AVATAR);
    }

    /**
     * Processes the user's input and displays responses in the chat interface.
     */
    private void processUserInput() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            appendMessage(userInput, USER_AVATAR);
            Response response = input.command(userInput);
            if (response == Response.TERMINATE) {
                terminateApplication();
            }
            appendMessage(response.toString(), CHATBOT_AVATAR);
            inputField.clear();
        }
    }

    /**
     * Appends a message to the chat interface with an associated avatar.
     *
     * @param message The message to be displayed.
     * @param imgSrc The source of the avatar image.
     */
    private void appendMessage(String message, String imgSrc) {
        ImageView avatarImageView = getAvatarImageView(imgSrc);
        HBox messageBox = createMessageBox(message, avatarImageView, imgSrc);
        chatBox.getChildren().add(messageBox);
    }

    /**
     * Creates a message box with an associated avatar for a message.
     *
     * @param message The message to be displayed.
     * @param avatarImageView The avatar image associated with the message.
     * @param imgSrc The source of the avatar image.
     * @return The message box.
     */
    private HBox createMessageBox(String message, ImageView avatarImageView, String imgSrc) {
        Text messageText = new Text(message);
        HBox messageContent;
        HBox messageBox = new HBox(10);

        switch (imgSrc) {
            case USER_AVATAR:
                messageContent = new HBox(10, avatarImageView, messageText);
                messageContent.setAlignment(Pos.BASELINE_LEFT);
                messageBox.setStyle("-fx-background-color: #ddf2e4; -fx-padding: 10px;");
                break;
            case CHATBOT_AVATAR:
                messageContent = new HBox(10, messageText, avatarImageView);
                messageContent.setAlignment(Pos.BASELINE_LEFT);
                messageBox.setStyle("-fx-background-color: #fedada; -fx-padding: 10px;");
                break;
            default:
                throw new DukeException("?");
        }

        messageBox.getChildren().add(messageContent);
        return messageBox;
    }

    /**
     * Terminates the Duke application.
     */
    private void terminateApplication() {
        System.exit(0);
    }

    /**
     * Retrieves an avatar ImageView from the specified image source.
     *
     * @param imgSrc The source of the avatar image.
     * @return An ImageView containing the avatar image.
     */
    private ImageView getAvatarImageView(String imgSrc) {
        ImageView avatarImageView = new ImageView(new Image(imgSrc));
        avatarImageView.setFitWidth(50);
        avatarImageView.setFitHeight(50);
        return avatarImageView;
    }
}
