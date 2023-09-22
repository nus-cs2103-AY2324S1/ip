package jarvis;

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

    private Jarvis jarvis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Users.jpg"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/Jarvis.jpg"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Jarvis object.
     *
     * @param d The Jarvis object.
     */
    public void setJarvis(Jarvis d) {
        jarvis = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getJarvisDialog(jarvis.getGreeting(), jarvisImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jarvis's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (!jarvis.hasExited()) {
            String input = userInput.getText();
            String response = jarvis.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJarvisDialog(response, jarvisImage)
            );
            userInput.clear();
        }
    }
}
