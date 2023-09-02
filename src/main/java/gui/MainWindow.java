package gui;

import jarvis.Jarvis;
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

    private Jarvis jarvis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJarvis(Jarvis j) {
        jarvis = j;
    }

    public void setGreetingMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getJarvisDialog(message, jarvisImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jarvis's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jarvis.respond(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJarvisDialog(response, jarvisImage)
        );
        userInput.clear();

        if (input.equals("exit")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
