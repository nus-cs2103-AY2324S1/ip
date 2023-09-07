package rocket;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    private Rocket rocket;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image rocketImage = new Image(this.getClass().getResourceAsStream("/images/DaRocket.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeText = "";
        welcomeText += "    Hello! I'm Rocket\n";
        welcomeText += "    What can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getRocketDialog(welcomeText, rocketImage));
    }

    public void setRocket(Rocket d) {
        rocket = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rocket.getResponse(input);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );


        // Create a timeline with a delay of 1 second (adjust as needed)
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(0.3),
                event -> dialogContainer.getChildren().add(
                        DialogBox.getRocketDialog(response, rocketImage)
                ))
        );

        // Play the timeline to introduce the delay
        timeline.play();

        userInput.clear();
    }
}
