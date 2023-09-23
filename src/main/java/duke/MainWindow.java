package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

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

    //The Duke class used.
    private Duke duke;

    //The image for the user.
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    //The image for the DukeBot
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/LatiBot.png"));

    /**
     * Initializes the chat window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Dukebot and greets the user.
     * @param d The Duke bot used.
     */
    public void setDuke(Duke d) {
        duke = d;
        String greet = Ui.greet();
        dialogContainer.getChildren().addAll(
                LatiBox.getDukeDialog(greet, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes for the user and Duke bot.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.messageHandler(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                LatiBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

