package sana;

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

    private Sana sana;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/img.png"));
    private Image sanaImage = new Image(this.getClass().getResourceAsStream("/images/img_2.png"));

    /**
     * Initializes the JavaFX controller.
     * This method is automatically called when the corresponding FXML file is loaded.
     * It sets up the initial dialogue in the chat interface and binds the scroll pane
     * to automatically scroll to the latest message.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getSanaDialog("Hi! I'm Sana. What can I do for you today?", sanaImage));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSana(Sana d) {
        sana = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sana.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSanaDialog(response, sanaImage)
        );
        userInput.clear();
    }
}
