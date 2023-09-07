package bruno;

import javafx.animation.PauseTransition;
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

    private Bruno bruno;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaHuman.png"));
    private Image brunoImage = new Image(this.getClass().getResourceAsStream("/images/DaDog.png"));
    private UI ui;

    @FXML private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ui = new bruno.UI();
        this.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(
                javafx.scene.paint.Color.GREEN, javafx.scene.layout.CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
    }

    public void setBruno(Bruno b) {
        bruno = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then
     * appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML private void handleUserInput() throws bruno.exceptions.BrunoException {
        String input = userInput.getText();
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        // Display the user dialog immediately
        dialogContainer.getChildren().add(userDialog);
        // Simulate a delay before Bruno's response
        PauseTransition userDelay = new PauseTransition(Duration.seconds(1));
        userDelay.setOnFinished(event -> {
            String response = bruno.getResponse(input);
            DialogBox dialogBox = DialogBox.getBrunoDialog(response, brunoImage);
            dialogContainer.getChildren().add(dialogBox);
            if (response.startsWith("Bye")) {
                PauseTransition exitDelay = new PauseTransition(Duration.seconds(1));
                exitDelay.setOnFinished(exitEvent -> {
                    System.exit(0);
                });
                exitDelay.play();
            }
        });
        userDelay.play();
        userInput.clear();
    }

    /**
     * Displays the greeting when the app starts up.
     */
    public void startUpBruno() {
        DialogBox brunoDialog = DialogBox.getBrunoDialog(ui.displayGreeting(), brunoImage);
        dialogContainer.getChildren().clear();
        dialogContainer.getChildren().add(brunoDialog);
    }
}
