package slay;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
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

    private Slay slay;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image slayImage = new Image(this.getClass().getResourceAsStream("/images/slay.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = Ui.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getSlayDialog(welcome, slayImage));
    }

    public void setSlay(Slay slay) {
        this.slay = slay;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Slay's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = slay.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox slayDialog = DialogBox.getSlayDialog(response, slayImage);
        dialogContainer.getChildren().addAll(userDialog, slayDialog);
        userInput.clear();

        if (input.equals("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
