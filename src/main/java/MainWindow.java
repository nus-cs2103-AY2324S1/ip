
import common.Message;
import exceptions.ThorndikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Thorndike thorndike;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Thorndike d) {
        thorndike = d;
    }

    public void greet() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Message.GREET, dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage));
        try {
            String response = thorndike.getResponse(input);
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(response, dukeImage));
        } catch (ThorndikeException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }

        userInput.clear();
    }
}
