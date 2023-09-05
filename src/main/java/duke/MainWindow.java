package duke;

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

    private Duke duke;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image cortana = new Image(this.getClass().getResourceAsStream("/images/cortana.png"));

    /**
     * Initialise the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox greeting = DialogBox.getDukeDialogBox(
                "Hello I'm Cortana, Microsoft killed me so now I'm here", cortana);
        dialogContainer.getChildren().add(greeting);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(input, user),
                DialogBox.getDukeDialogBox(response, cortana)
        );
        userInput.clear();
    }
}
