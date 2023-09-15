package chadbod;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class represents a controller for MainWindow. Provides the layout for the other controls.
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

    private ChadBod chadBod;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/you.png"));
    private Image chadImage = new Image(this.getClass().getResourceAsStream("/images/chad.png"));

    /**
     * Initializes the MainWindow by displaying a greeting message from ChadBod in the chat interface, as well as
     * binding the scroll pane's vertical value to the dialog container's height for automatic scrolling.
     */
    @FXML
    public void initialize() {
        String greetingText = Ui.displayGreeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getChadDialog(greetingText, chadImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChadBod(ChadBod d) {
        chadBod = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChadBod's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String chadText = chadBod.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getChadDialog(chadText, chadImage)
        );
        userInput.clear();
    }
}
