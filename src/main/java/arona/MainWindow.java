package arona;

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
 * Controller for the main application window. This class provides the layout for the user interface controls.
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

    private Arona arona;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image aronaImage = new Image(this.getClass().getResourceAsStream("/images/arona_icon.jpg"));


    /**
     * Initializes the main window.
     * Binds the scroll pane's vertical value to the height of the dialog container.
     * Displays a welcome message from Arona.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        DialogBox welcomeDialog = DialogBox.getAronaDialog("Hello! I'm Arona, your Virtual Assistant.", aronaImage);
        welcomeDialog.setMessageType(DialogBox.MessageType.ARONA);
        dialogContainer.getChildren().add(welcomeDialog);
    }

    /**
     * Sets the Arona instance for the main window controller.
     *
     * @param a The Arona instance to be set.
     */
    public void setArona(Arona a) {
        arona = a;
    }

    /**
     * Handles user input.
     * Creates dialog boxes for user input and Arona's reply and appends them to the dialog container.
     * Clears the user input after processing.
     * Exits the application if the user enters "bye" after a 2-second pause.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().toLowerCase().trim();
        if (input.equals("bye")) {
            DialogBox goodbyeDialog = DialogBox.getAronaDialog("Goodbye. See you soon!", aronaImage);
            goodbyeDialog.setMessageType(DialogBox.MessageType.ARONA);

            dialogContainer.getChildren().add(
                    goodbyeDialog);

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();

            return;
        }

        // Add a 0.5-second delay before displaying Arona's response
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        userDialog.setMessageType(DialogBox.MessageType.USER);

        dialogContainer.getChildren().addAll(
                userDialog
        );

        PauseTransition responseDelay = new PauseTransition(Duration.seconds(0.5));
        responseDelay.setOnFinished(event -> {
            String response = arona.getResponse(input);
            DialogBox aronaDialog = DialogBox.getAronaDialog(response, aronaImage);

            aronaDialog.setMessageType(DialogBox.MessageType.ARONA);
            dialogContainer.getChildren().addAll(
                    aronaDialog
            );
        });
        userInput.clear();
        responseDelay.play();
    }
}
