package design;
import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private String initialDialog = "You know that I never let you down :)"
            + "\nApplication does not auto-save, you need to enter 'bye' when you want to save and exit";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage;
    private Image dukeImage;

    @FXML
    public void initialize() {
        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(initialDialog, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
        d.initialise();
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
