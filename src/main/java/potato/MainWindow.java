package potato;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Potato potato;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image potatoImage = new Image(this.getClass().getResourceAsStream("/images/DaPotato.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPotato(Potato p) {
        potato = p;
        dialogContainer.getChildren().add(
                DialogBox.getPotatoDialog("Hey GURL I'm Potato!\n" + "How can I help ya?", potatoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = potato.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPotatoDialog(response, potatoImage)
        );
        if (input.equals("bye")) {
            Duration delay = Duration.seconds(2);
            KeyFrame keyFrame = new KeyFrame(delay, event -> Platform.exit());
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(1);
            timeline.play();
        }
        userInput.clear();
    }

}

