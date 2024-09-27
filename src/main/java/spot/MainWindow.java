package spot;

import javafx.animation.PauseTransition;
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

    private Spot spot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image spotImage = new Image(this.getClass().getResourceAsStream("/images/Spot.png"));

    /**
     * Initializes appearance of the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greetUser();
    }

    /**
     * Sets Spot chatbot.
     */
    public void setSpot(Spot s) {
        spot = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Spot's reply and then appends them to
     * the dialog container. Clears the user input after processing. Closes chatbot on 'bye' exit command.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = spot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSpotDialog(response, spotImage)
        );
        userInput.clear();
        if (spot.getExit()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
    }

    /**
     * Creates dialog box to greet user on initial chatbot startup.
     */
    @FXML
    private void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getSpotDialog("Hello, it's Spot!", spotImage)
        );
    }
}
