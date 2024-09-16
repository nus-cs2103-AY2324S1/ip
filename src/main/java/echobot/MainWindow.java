package echobot;

import javafx.fxml.FXML;
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

    private EchoBot echobot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setEchoBot(EchoBot echobot) {
        this.echobot = echobot;
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getEchoBotDialog(dukeText, dukeImage)
        );
        userInput.clear();
        if (!echobot.isRunning()) {
            System.exit(0);
        }
    }

    /**
     * Outputs greetings by chatbot at the start of the app
     */
    public void greetUser() {
        EchoBot.startBot();
        String greetings = "Welcome, User\n" + "What would you like to do?";
        dialogContainer.getChildren().addAll(
                DialogBox.getEchoBotDialog(greetings, dukeImage)
        );
    }

    public String getResponse(String input) {
        return echobot.getResponse(input);
    }
}
