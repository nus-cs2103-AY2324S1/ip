package smolbrain;

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

    private Smolbrain smolbrain;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Constructor for this class.
     */
    public MainWindow() {
    }

    /**
     * Initialise the scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates and run a new Smolbrain object.
     */
    public void createSmolbrain() {
        smolbrain = new Smolbrain("data.txt", this);
        smolbrain.run();
    }

    /**
     * Retrieves the user's input and processes the command, then clear the user input field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        displayUserText(input);
        smolbrain.process(input);
        userInput.clear();
    }

    /**
     * Display the provided input string as a user dialog.
     *
     * @param input String to display.
     */
    public void displayUserText(String input) {
        dialogContainer.getChildren().addAll(
            UserDialog.getDialog(input, userImage)
        );
    }

    /**
     * Display the provided input string as a bot dialog.
     *
     * @param input String to display.
     */
    public void displayBotText(String input) {
        dialogContainer.getChildren().addAll(
            BotDialog.getDialog(input, botImage)
        );
    }

}
