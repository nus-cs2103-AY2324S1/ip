package URBOI_PACKIN.UI;

import URBOI_PACKIN.ResponseController;
import URBOI_PACKIN.UI.DialogBox;
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


    private ResponseController responseController;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/crying.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/screaming.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greetingMessage =
                "Wazzup! It's\n"
                + "##__________##__########_______########______#######_____#####_\n"
                + "##__________##__##________##_____##________##__##_________##____##____\n"
                + "##__________##__##________##_____##________##__##_________##____##____\n"
                + "##__________##__########_______########____##__________##____##____\n"
                + "##__________##__##______##_______##________##__##_________##____##____\n"
                + "##__________##__##________##_____##________##__##_________##____##____\n"
                + "__#######______##__________##___########______#######_____#####_\n"
                + "What can I do for you mah man?";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetingMessage, dukeImage));
    }
    public void setDuke(ResponseController d) {
        responseController = d;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing URBOI_PACKIN.ResponseController's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = responseController.getResponse(input);
        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}