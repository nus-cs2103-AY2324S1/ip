package joe.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import joe.Joe;

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

    private Joe joe;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image joeImage = new Image(this.getClass().getResourceAsStream("/images/joever2.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getJoeDialog(greet(), joeImage)
        );
    }

    /**
     * Sets the joe chat-bot.
     *
     * @param j The Joe chat-bot
     */
    public void setJoe(Joe j) {
        joe = j;
    }

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return ("Hello! I'm Joe\nWhat can I do for you?");
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Joe's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = joe.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJoeDialog(response, joeImage)
        );
        userInput.clear();
    }
}
