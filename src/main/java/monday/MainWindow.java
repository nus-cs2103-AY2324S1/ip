package monday;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import monday.monday.ui.Ui;

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

    private Monday monday;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bai.png"));
    private Image mondayImage = new Image(this.getClass().getResourceAsStream("/images/fa.png"));

    /**
     * Start the programme and greets the user on start of program.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.greet(), mondayImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMonday(Monday d) {
        monday = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(Ui.exit(), mondayImage)
            );
            userInput.clear();
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            delay.play();
            return;
        }

        String response = monday.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, mondayImage)
        );

        userInput.clear();
    }
}
