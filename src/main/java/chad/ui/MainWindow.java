package chad.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import chad.chatengine.ChatEngine;
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

    private ChatEngine chad;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/sadPepe.png"));
    private final Image chadImage = new Image(this.getClass().getResourceAsStream("/images/chadSquidward.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChad(ChatEngine d) {
        chad = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chad's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chad.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChadDialog(response, chadImage)
        );
        userInput.clear();
    }
}

