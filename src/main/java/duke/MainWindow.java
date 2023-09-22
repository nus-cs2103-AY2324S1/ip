package duke;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should not be null in initialize method.";
        assert dialogContainer != null : "DialogContainer should not be null in initialize method.";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        assert duke != null : "Duke object should not be null when setting in MainWindow.";
        this.duke = duke;
        String welcomeMessage = "Hello! I'm Johnnythesnake, your personal chatbot. How can I assist you today?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert input != null : "User input should not be null in handleUserInput method.";
        assert response != null : "Response should not be null in handleUserInput method.";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    public void addToDialog(String message) {
        Label label = new Label(message);
        dialogContainer.getChildren().add(label);
    }

}
