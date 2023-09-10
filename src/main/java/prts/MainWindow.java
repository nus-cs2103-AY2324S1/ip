package prts;

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

    private Prts prts;

    private final Image docImage = new Image(this.getClass().getResourceAsStream("/images/doc.jpg"));
    private final Image prtsImage = new Image(this.getClass().getResourceAsStream("/images/prts.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Prts d) {
        prts = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = prts.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, docImage),
                DialogBox.getPrtsDialog(response, prtsImage)
        );
        userInput.clear();
    }
}
