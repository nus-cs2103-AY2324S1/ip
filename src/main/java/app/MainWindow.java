package app;
import james.James;
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

    private James james = new James();

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(james.getWelcomeMessage(), bot)
        );
    }

    /**
     * Sets the James object.
     * @param d The James object.
     */
    public void setJames(James d) {
        james = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getBotDialog(response, bot)
        );
        userInput.clear();
    }

    /**
     * Returns the response from bot.
     */
    private String getResponse(String input) {
        String output = this.james.processInput(input);
        return output;
    }
}
