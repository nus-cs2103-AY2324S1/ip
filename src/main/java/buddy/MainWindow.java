package buddy;

import javafx.application.Platform;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Buddy buddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image buddyImage = new Image(this.getClass().getResourceAsStream("/images/Buddy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the buddy bot.
     * @param b buddy instance
     */
    public void setBuddy(Buddy b) {
        buddy = b;
        dialogContainer.getChildren().add(DialogBox.getBuddyDialog(buddy.getGreeting(), buddyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = buddy.getResponse(input);

        if (response.trim().isBlank()) {
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
            userInput.clear();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBuddyDialog(response, buddyImage)
        );
        userInput.clear();

        if (buddy.getExitStatus()) {
            Stage currentStage = (Stage) dialogContainer.getScene().getWindow();
            Platform.runLater(() -> {
                currentStage.close();
            });
        }
    }
}
