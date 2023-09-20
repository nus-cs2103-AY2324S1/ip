package duke;

import java.io.IOException;

import duke.assets.storage.Storage;
import duke.assets.ui.DialogBox;
import duke.dukeexceptions.CorruptDataException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class for the ChadGPT chatbot application.
 */
public class Duke extends Application {
    private final Storage storage = new Storage();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream(
            "/images/userAvatar.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream(
            "/images/botAvatar.png"));

    @Override
    public void start(Stage stage) {
        setComponents(stage);
        addFunctionality();
        greetUser();
    }

    /**
     * Sets up the required components of UI
     *
     * @param stage UI stage
     */
    private void setComponents(Stage stage) {
        // Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        formatWindow(stage, mainLayout);
    }

    /**
     * Format the UI window
     *
     * @param stage ui stage
     * @param mainLayout layout of UI
     */
    private void formatWindow(Stage stage, AnchorPane mainLayout) {
        // Step 2. Formatting the window to look as expected
        stage.setTitle("ChadGPT");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Add functionality to UI handle user input
     */
    private void addFunctionality() {
        // Part 3. Add functionality to handle user input.
        readFromData();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates dialog boxes for user and chatbot given some user input
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String chatbotResponse = storage.passUserCommand(userInput.getText());
        Label dukeText = new Label(getResponse(chatbotResponse));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Formats the text response for the chatbot
     *
     * @param input text response from the chatbot
     * @return full text response from the chatbot as a string
     */
    private String getResponse(String input) {
        return "ChadGPT: " + input;
    }

    /**
     * Creates a DialogueBox for the chatbot, without user prompt
     *
     * @param message text to be displayed by chatbot
     */
    private void dukeMessage(String message) {
        Label dukeText = new Label(getResponse(message));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }

    /**
     * Greets the user
     */
    private void greetUser() {
        dukeMessage("Hello I'm ChadGPT, what do you want to do today?");
    }

    /**
     * Read data file to retrieve saved tasks from memory, in the case of unsuccessful retrieval,
     * highlights the corrupt input and prompts user to restart the program
     */
    private void readFromData() {
        try {
            storage.readFromFile();
        } catch (IOException e) {
            dukeMessage("Unfortunately there was an unexpected error when reading your data file."
                    + "Please check your I/O devices and restart the program");
            sendButton.setDisable(false);
            userInput.setDisable(false);
        } catch (CorruptDataException e) {
            dukeMessage("Data is corrupt at: \"" + e.getCorruptLine() + "\". Please fix and restart the chatbot.");
            sendButton.setDisable(false);
            userInput.setDisable(false);
        } catch (Exception e) {
            dukeMessage("Unkown exception. Please restart the program.");
            sendButton.setDisable(false);
            userInput.setDisable(false);
        }
    }
}
