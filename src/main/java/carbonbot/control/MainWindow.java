package carbonbot.control;

import java.util.Objects;

import carbonbot.CarbonBot;
import javafx.application.Platform;
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

    private CarbonBot carbonbot;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.png")));
    private final Image carbonImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/carbonbot.png")));

    /**
     * Initializes the MainWindow and adds a greeting message by the bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Adds a message by the CarbonBot.
     * @param message Message to be displayed
     */
    public void addCarbonDialog(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getCarbonDialog(message, carbonImage)
        );
    }

    public void setBot(CarbonBot carbonbot) {
        this.carbonbot = carbonbot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing CarbonBot's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = carbonbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCarbonDialog(response, carbonImage)
        );
        userInput.clear();

        if (carbonbot.shouldExit()) {
            Platform.exit();
        }
    }
}
