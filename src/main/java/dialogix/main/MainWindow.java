package dialogix.main;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window of the Dialogix application. Provides the layout for the UI controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Dialogix dialogix;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/userImage.png")));
    private final Image dialogixImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/dialogixImage.png")));

    /**
     * Initializes the main window controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Sets the Dialogix instance for handling user interactions.
     *
     * @param dialogix The Dialogix instance.
     */
    public void setDialogix(Dialogix dialogix) {
        this.dialogix = dialogix;
    }

    /**
     * Handles user input, processes it, and displays the user's input and Dialogix's response in the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dialogix.getResponse(input);

        if (response.trim().isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );
            userInput.clear();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDialogixDialog(response, dialogixImage)
        );
        userInput.clear();
    }

    /**
     * Displays a welcome message from Dialogix in the dialog container.
     */
    @FXML
    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDialogixDialog("Welcome to Dialogix!", dialogixImage)
        );
    }
}
