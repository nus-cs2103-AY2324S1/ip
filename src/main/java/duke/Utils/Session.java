package duke.utils;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Session class represents a user session in the Duke application.
 * It manages user input and responses during the session.
 */
public class Session extends Application {
    private static final String IMG_DIR = "file:img/";
    private static final String CHATBOT_AVATAR = Session.IMG_DIR + "pb.jpg";
    private static final String USER_AVATAR = Session.IMG_DIR + "rs.jpg";

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
        BorderPane root = new BorderPane();
        this.chatBox = new VBox(10);
        this.chatBox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        this.inputField = new TextField();
        this.inputField.setPromptText("Enter your command...");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> processUserInput());

        ImageView userImageView = getAvatarImageView(Session.USER_AVATAR);
        ImageView chatbotImageView = getAvatarImageView(Session.CHATBOT_AVATAR);

        HBox inputBox = new HBox(10);
        inputBox.setPadding(new Insets(10));
        inputBox.getChildren().addAll(userImageView, inputField, sendButton);

        root.setCenter(scrollPane);
        root.setBottom(inputBox);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Duke Chatbot");
        primaryStage.setScene(scene);
        primaryStage.show();

        appendMessage(
            Response.GREETINGS.toString(),
            Session.CHATBOT_AVATAR
        );
    }

    /**
     * Processes the user's input and displays responses in the chat interface.
     */
    private void processUserInput() {
        String userInput = this.inputField.getText().trim();
        if (!userInput.isEmpty()) {
            appendMessage(userInput, Session.USER_AVATAR);
            Response response = this.input.command(userInput);
            if (response == Response.TERMINATE) {
                terminateApplication();
            }
            appendMessage(response.toString(), Session.CHATBOT_AVATAR);
            this.inputField.clear();
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
        HBox messageBox = new HBox(10);
        Label messageLabel = new Label(message);
        HBox messageContent;

        switch (imgSrc) {
        case Session.USER_AVATAR:
            messageContent = new HBox(10, avatarImageView, messageLabel);
            messageContent.setAlignment(Pos.BASELINE_LEFT);
            messageBox.setStyle("-fx-background-color: #ddf2e4; -fx-padding: 10px;");
            break;
        case Session.CHATBOT_AVATAR:
            messageContent = new HBox(10, messageLabel, avatarImageView);
            messageContent.setAlignment(Pos.BASELINE_LEFT);
            messageBox.setStyle("-fx-background-color: #fedada; -fx-padding: 10px;");
            break;
        default:
            throw new DukeException("?");
        }

        messageBox.getChildren().add(messageContent);
        chatBox.getChildren().add(messageBox);
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
