package cheems.gui;

import cheems.Cheems;
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

    private Cheems cheems;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/samoyed.png"));
    private Image cheemsImage = new Image(this.getClass().getResourceAsStream("/images/cheems.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCheems(Cheems c) {
        cheems = c;
    }

    public void showWelcomeDialog() {
        String welcomeMsg = cheems.getWelcomeMsg();
        dialogContainer.getChildren().addAll(
                DialogBox.getCheemsDialog(welcomeMsg, cheemsImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing cheems's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cheems.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCheemsDialog(response, cheemsImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
    }
}

