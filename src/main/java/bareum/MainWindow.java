package bareum;

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
    private Bareum bareum;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/doeun.jpg"));
    private Image bareumImage = new Image(this.getClass().getResourceAsStream("/images/bareum.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getBareumDialog(Ui.getWelcomeMessage(), bareumImage)
        );
    }

    public void setBareum(Bareum b) {
        bareum = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBareumDialog(response, bareumImage)
        );
        userInput.clear();
    }

    /**
     * Gets Bareum's response to the command received.
     * @param input Command user wants to perform.
     * @return Bareum's response to the command the user has input.
     */
    private String getResponse(String input) {
        return bareum.run(input);
    }
}
