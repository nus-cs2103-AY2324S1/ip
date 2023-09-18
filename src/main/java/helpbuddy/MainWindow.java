package helpbuddy;

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

    private HelpBuddy helpBuddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image helpBuddyImage = new Image(this.getClass().getResourceAsStream("/images/HelpBuddy.png"));

    /**
     * Initializes MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getHelpBuddyDialog(HelpBuddy.sayHello(), helpBuddyImage)
        );
    }

    public void setHelpBuddy(HelpBuddy d) {
        assert d != null;
        helpBuddy = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing HelpBuddy's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = helpBuddy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHelpBuddyDialog(response, helpBuddyImage)
        );
        userInput.clear();
    }

    /**
     * Creates a dialog box containing HelpBuddy's reply when an error is encountered.
     * @param  error A String representation of error message.
     */
    @FXML
    protected void printErrorMessage(String error) {
        dialogContainer.getChildren().addAll(
                DialogBox.getHelpBuddyDialog(error, helpBuddyImage)
        );
    }
}
