package monke;

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

    private Monke monke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bingChilling.png"));
    private Image monkeImage = new Image(this.getClass().getResourceAsStream("/images/monke.jpeg"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getMonkeDialog("Hello, I'm Monke. OOGA BOOGA!\nWhat can I do for you?", monkeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMonke(Monke monke) {
        this.monke = monke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = monke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMonkeDialog(response, monkeImage)
        );
        userInput.clear();
    }
}
