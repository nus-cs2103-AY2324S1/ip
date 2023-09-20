package ipbot.controller;

import ipbot.Duke;
import ipbot.control.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindowController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private static final Image userImage = new Image(MainWindowController.class.getResourceAsStream("/images/DaUser.png"));
    private static final Image botImage = new Image(MainWindowController.class.getResourceAsStream("/images/DaDuke.png"));

    private static final String WELCOME_MESSAGE = "Hello I'm Ip Bot!\n" +
            "While I may not be able to fight like Ip Man, I can assist you in other areas!\n" +
            "What can I do for you?";

    @FXML
    public void initialize() {
        assert this.scrollPane != null : "Scroll pane component not found";
        assert this.dialogContainer != null : "Dialog container component not found";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the bot reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert this.userInput != null : "User input component not found";
        assert this.duke != null : "Duke not set";
        assert this.dialogContainer != null : "Dialog container component not found";
        String userText = this.userInput.getText();
        String botText = duke.getResponse(this.userInput.getText());
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBotDialog(botText, botImage)
        );
        this.userInput.clear();
    }

    /**
     * Sends the welcome message to the user.
     */
    public void sendWelcome() {
        assert this.dialogContainer != null : "Dialog container component not found";
        this.dialogContainer.getChildren().add(DialogBox.getBotDialog(WELCOME_MESSAGE, botImage));
    }
}
