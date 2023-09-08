package GUI;
import Duke.Duke;
import OOP.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/eve.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/wallE.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // to greet the user:
        Label greetingLabel = new Label(new Ui().printGreeting("wallE"));
        Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/wallE.png"));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingLabel.getText(), dukeImage));
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(duke.getResponse(userInput.getText()));
        DialogBox userDialog = DialogBox.getUserDialog(userText.getText(), userImage);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText.getText(), dukeImage);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        if (dukeText.getText().equals("Bye. Hope to see you again soon! I'll close the window now.\n")) {
            Stage stage = (Stage) userInput.getScene().getWindow(); // Get the stage
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( e -> stage.close() );
            delay.play();
        }
        userInput.clear();
    }
}
