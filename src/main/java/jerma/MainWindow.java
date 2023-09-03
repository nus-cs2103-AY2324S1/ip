package jerma;

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

    private Jerma jerma;

    private Image sterImage = new Image(
            this.getClass().getResourceAsStream("/images/ster.jpg"));
    private Image jermaImage = new Image(
            this.getClass().getResourceAsStream("/images/jerma.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJerma(Jerma jerma) {
        this.jerma = jerma;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container. Clears the
     * user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jerma.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, sterImage),
                DialogBox.getJermaDialog(response, jermaImage));
        userInput.clear();
    }
}
