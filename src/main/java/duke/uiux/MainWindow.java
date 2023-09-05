package duke.uiux;
import duke.ModelViewController;
import duke.Response;
import duke.uiux.DialogBox;
import duke.uiux.Gui;
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

    private Gui gui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        createWelcomeMessage();
    }

    public void setGui(Gui g) {
        gui = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gui.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates a welcome message for the user
     */
    public void createWelcomeMessage() {
        ModelViewController mvc = new ModelViewController();
        Response welcomeResponse = mvc.createWelcomeMessage();
        String welcomeMessage = welcomeResponse.getResponse();
        DialogBox welcomeDialog = DialogBox.getDukeDialog(welcomeMessage, dukeImage);
        dialogContainer.getChildren().addAll(welcomeDialog);
    }
}

