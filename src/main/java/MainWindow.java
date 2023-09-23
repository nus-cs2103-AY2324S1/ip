import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Stage stage;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void displayMessage(String message) {
        Label dukeResponseLabel = new Label(message);
        ImageView dukeImageView = new ImageView(dukeImage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeResponseLabel, dukeImageView));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        Label userInputLabel = new Label(input);
        ImageView userImageView = new ImageView(userImage);

        Label dukeResponseLabel = new Label(response);
        ImageView dukeImageView = new ImageView(dukeImage);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInputLabel, userImageView),
                DialogBox.getDukeDialog(dukeResponseLabel, dukeImageView)
        );

        userInput.clear();
    }

    public void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }

}
