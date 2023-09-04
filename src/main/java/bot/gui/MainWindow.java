package bot.gui;

import bot.Bot;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /**
     * Scroll pane on the GUI.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * Dialog container for the GUI.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * User input field for the GUI
     */
    @FXML
    private TextField userInput;

    /**
     * Bot instance exchanging data with the GUI.
     */
    private Bot bot;

    /**
     * User's image in the GUI.
     */
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    /**
     * Bot's image in the GUI.
     */
    private final Image botImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaBot.png")));

    /**
     * Initialises the main window in the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the active bot instance to the given bot.
     *
     * @param bot Bot to use in the GUI.
     */
    public void setBot(Bot bot) {
        this.bot = bot;
        dialogContainer.getChildren().add(DialogBox.getBotDialog(bot.greet(), botImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (bot.isExit()) {
            return;
        }
        String input = userInput.getText();
        String response = bot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}
