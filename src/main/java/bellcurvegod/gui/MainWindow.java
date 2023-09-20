package bellcurvegod.gui;

import bellcurvegod.BellCurveGod;
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

    private BellCurveGod bellCurveGod;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image bellCurveGodImage = new Image(this.getClass().getResourceAsStream("/images/bellCurveGod.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBellCurveGod(BellCurveGod b) {
        bellCurveGod = b;
        // greets the user
        printMessage(Gui.getWelcomeMessage());
    }

    public void printMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getBellCurveGodDialog(message, bellCurveGodImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bellCurveGod.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getBellCurveGodDialog(response, bellCurveGodImage)
        );
        if (response.equals(Gui.getExitMessage())) {
            // Solution below adapted by
            // https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx

            // If user enters "bye", display the ExitMessage, wait for 2 seconds, and close the app
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
        userInput.clear();
    }
}
