package brandon.chatbot.ui;

import brandon.chatbot.DialogBox;
import brandon.chatbot.Ekud;
import brandon.chatbot.commands.generalcommands.HelpCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String WELCOME_MESSAGE = "Hello... I am ekuD... \n\n";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ekud ekuD;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ekuD.png"));

    /**
     * Initializes the scene with welcome and a help message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(WELCOME_MESSAGE + HelpCommand.HELP_MESSAGE, dukeImage)
        );
    }

    public void setDuke(Ekud d) {
        ekuD = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        String response = ekuD.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (response.equals("bye bye...")) {
            handleExit();
        } else {
            userInput.clear();
        }
    }
    @FXML
    private void handleExit() {
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        stage.close();
    }
}
