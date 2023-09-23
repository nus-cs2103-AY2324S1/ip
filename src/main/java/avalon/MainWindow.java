package avalon;

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

    private Avalon avalon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Artoria.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Arthur.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAvalon(Avalon a) {
        avalon = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = avalon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAvalonDialog(response, dukeImage)
        );

        if (response.equals("Farewell. We will meet again!\n")) {
            PauseTransition wait = new PauseTransition(Duration.seconds(1));
            wait.setOnFinished((e) -> {
                Stage stage = (Stage) sendButton.getScene().getWindow();
                stage.close();
            });
            wait.play();
        }

        userInput.clear();
    }
}
