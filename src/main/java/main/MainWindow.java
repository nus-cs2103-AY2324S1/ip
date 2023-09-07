package main;

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

    private Dialogix dialogix;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image dialogixImage = new Image(this.getClass().getResourceAsStream("/images/dialogixImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    public void setDialogix(Dialogix d) {
        dialogix = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dialogix.getResponse(input);

        if (response.trim().isBlank()) {
            dialogContainer.getChildren().addAll(
                    DialogixBox.getUserDialog(input, userImage)
            );
            userInput.clear();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogixBox.getUserDialog(input, userImage),
                DialogixBox.getDialogixDialog(response, dialogixImage)
        );
        userInput.clear();
    }

    @FXML
    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogixBox.getDialogixDialog("Welcome to Dialogix!", dialogixImage)
        );
    }
}
