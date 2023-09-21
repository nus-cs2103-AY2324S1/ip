package teho.main;

import javafx.fxml.FXML;

import javafx.animation.PauseTransition;

import javafx.util.Duration;

import javafx.application.Platform;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import teho.exceptions.InvalidCommandException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TehO tehO;

    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/User.png"));
    private Image tehOImage = new Image(this.getClass()
            .getResourceAsStream("/images/TehO.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String helloMessage = new Ui().generateHelloMessage();
        dialogContainer.getChildren().add(DialogBox.getTehODialog(helloMessage, tehOImage));
    }

    public void setTehO(TehO t) {
        tehO = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * TehO's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
            String response = tehO.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getTehODialog(response, tehOImage)
            );
            if (input.equalsIgnoreCase("bye")) {
                //solution inspired by
                //https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    userInput.clear();
                    Platform.exit();
                });
                pause.play();
            }
            userInput.clear();
        }
}