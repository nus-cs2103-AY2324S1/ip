package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;

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

    @FXML
    private ImageView userImageView;
    @FXML
    private ImageView dialogixImageView;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();

        // Load and set images using ImageView
        InputStream userImageStream = this.getClass().getResourceAsStream("/media/image/userImage.png");
        InputStream dialogixImageStream = this.getClass().getResourceAsStream("/media/image/dialogixImage.png");

        if (userImageStream != null && dialogixImageStream != null) {
            Image userImage = new Image(userImageStream);
            Image dialogixImage = new Image(dialogixImageStream);

            userImageView.setImage(userImage);
            dialogixImageView.setImage(dialogixImage);
        } else {
            // Handle resource loading failure
            System.err.println("Failed to load image resources.");
        }
    }

    public void setDialogix(Dialogix d) {
        dialogix = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dialogix's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dialogix.getResponse(input);

        if (response.trim().isBlank()) {
            dialogContainer.getChildren().addAll(
                    DialogixBox.getUserDialog(input, userImageView.getImage())
            );
            userInput.clear();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogixBox.getUserDialog(input, userImageView.getImage()),
                DialogixBox.getDialogixDialog(response, dialogixImageView.getImage())
        );
        userInput.clear();
    }

    @FXML
    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogixBox.getDialogixDialog("Welcome to Dialogix!", dialogixImageView.getImage())
        );
    }
}
