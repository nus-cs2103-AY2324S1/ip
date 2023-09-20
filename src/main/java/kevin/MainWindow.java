package kevin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kevin.ui.DialogBox;
import kevin.ui.Logger;

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

    private Kevin kevin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image kevinImage = new Image(this.getClass().getResourceAsStream("/images/kevin.png"));

    /**
     * Initialises Kevin
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getKevinDialog(Logger.hello(), kevinImage)
        );
    }

    /**
     * Set Kevin to the correct object.
     * @param k This is the Kevin object.
     */
    public void setKevin(Kevin k) {
        kevin = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kevin's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kevin.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKevinDialog(response, kevinImage)
        );
        userInput.clear();
    }
}

