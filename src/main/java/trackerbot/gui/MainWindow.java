package trackerbot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import trackerbot.TrackerBot;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * <p>Skeleton code from the JavaFX tutorial provided.</p>
 * <p>Note that @FXML exposes the private methods to FXML files.</p>
 *
 * @version Level-10
 */
public class MainWindow extends AnchorPane {
    /** Container that expands to have a ScrollBar, to use in conjunction with dialogContainer. */
    @FXML
    private ScrollPane scrollPane;

    /** Container to hold all the DialogBox instances. */
    @FXML
    private VBox dialogContainer;


    /** Text Field for user input. */
    @FXML
    private TextField userInput;

    /** Button to send user input to program. */
    @FXML
    private Button sendButton;

    /** TrackerBot instance, serving as a backend. */
    private TrackerBot trackerBot;

    /** User image of TrackerBot, referenced by DialogBox. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    /** Bot image of TrackerBot, referenced by DialogBox. */
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the TrackerBot instance to the initialized instance in Main.
     * <p>Adapted skeleton code to also immediately display the welcome message
     * on initialization.</p>
     * @param bot The instance to preserve in MainWindow.
     */
    public void setTrackerBot(TrackerBot bot) {
        trackerBot = bot;
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(trackerBot.getLastMessage(), botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = trackerBot.handleInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}
