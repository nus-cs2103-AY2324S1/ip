package chadbod;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class represents a controller for MainWindow and provides the layout for the other controls.
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
     * enabling automatic scrolling.
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
