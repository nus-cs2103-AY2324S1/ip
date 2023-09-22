package aichan.gui;

import aichan.AiChan;
import aichan.Ui;
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

    private AiChan aiChan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image aiChanImage = new Image(this.getClass().getResourceAsStream("/images/DaAiChan.png"));

    /**
     * Initializes the state of chat interface.
     * Bind the scroll position and display a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = Ui.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getAiChanDialog(welcomeMessage, aiChanImage)
        );
    }

    /**
     * Sets aiChan.
     *
     * @param aiChan An AiChan object.
     */
    public void setAiChan(AiChan aiChan) {
        this.aiChan = aiChan;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aiChan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAiChanDialog(response, aiChanImage)
        );
        userInput.clear();
    }
}
