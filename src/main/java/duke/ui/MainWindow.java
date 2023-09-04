package duke.ui;

import duke.Duke;
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

    private Duke duke;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image bot = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    /**
     * Initialises the ScrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Ace\nWhat can I do for you?", this.bot)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        duke.run(input);
        String response = duke.ui.getOutput();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.user),
                DialogBox.getDukeDialog(response, this.bot)
        );
        userInput.clear();
    }
}
