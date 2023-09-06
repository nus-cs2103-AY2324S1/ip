package ui;

import chatbot.evan.Evan;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.DialogBox;

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

    private Evan evan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image evanImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setEvan(Evan d) {
        evan = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = evan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEvanDialog(response, evanImage)
        );
        userInput.clear();
    }

    public void onStart() {
        String message = evan.getIntro();
        dialogContainer.getChildren().addAll(
                DialogBox.getEvanDialog(message, evanImage)
        );
    }
}
