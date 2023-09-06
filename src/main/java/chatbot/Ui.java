package chatbot;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.function.Consumer;

public class Ui {
    private Stage primaryStage;
    private TextArea displayArea;
    private TextField inputField;


    // Callback function to handle user input
    private Consumer<String> userInputCallback;

    private VBox dialogContainer; // This will hold all dialog boxes
    private ScrollPane scrollPane; // This will wrap around the dialogContainer for scrolling

    /**
     * constructor for Ui class.
     */
    public Ui(Stage stage, Consumer<String> userInputCallback) {
        this.primaryStage = stage;
        this.userInputCallback = userInputCallback;
        initializeUi();
    }

    private void initializeUi() {
        // Create a display area for chatbot responses
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setWrapText(true);

        dialogContainer = new VBox(10);

        scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);

        Image botImage = loadImage("/DaUser.png");
        Label greetLabel = new Label("Hi, I am Sara. What can I do for you?");
        DialogBox greetingDialog = DialogBox.getDukeDialog(greetLabel, new ImageView(botImage));
        dialogContainer.getChildren().add(greetingDialog);

        // Create an input field for user input
        inputField = new TextField();
        inputField.setPromptText("Enter your command here...");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());


        Button sendButton = new Button("Send");

        sendButton.setOnAction(event -> {
            String userInput = inputField.getText();
            handleUserInput(userInput);  // Handle input in the UI class
            userInputCallback.accept(userInput); // Pass input to Chatbot
        });

        // Layout components vertically in a VBox layout
        VBox layout = new VBox(10, scrollPane, inputField, sendButton);
        Scene scene = new Scene(layout, 400, 300);

        primaryStage.setTitle("ChatBot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void displayUserText(String text, Image userImage) {
        Label userText = new Label(text);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(userImage)));
    }

    public void displayBotText(String text, Image botImage) {
        Label botText = new Label(text);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(botText, new ImageView(botImage)));
    }

    private void handleUserInput(String userInput) {
        displayArea.appendText("User: " + userInput + "\n");
        inputField.clear();
    }


    private Image loadImage(String path) {
        InputStream imageStream = this.getClass().getResourceAsStream(path);
        if (imageStream == null) {
            System.err.println("Failed to load image from path: " + path);
            throw new RuntimeException("Image not found: " + path);
        }
        return new Image(imageStream);
    }

    public Stage getStage() {
        return primaryStage;
    }
}


