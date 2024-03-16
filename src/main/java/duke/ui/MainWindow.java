package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Duke duke;

    private Image userImage;
    private Image dukeImage;

    /**
     * Initializes the MainWindow. Binds the ScrollPane's vertical value to the height of the dialog container, ensuring
     * automatic scrolling as new messages are added.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane is not initialized";
        assert dialogContainer != null : "DialogContainer is not initialized";
        assert userInput != null : "UserInput is not initialized";
        assert sendButton != null : "SendButton is not initialized";

        java.io.InputStream userImagePath = this.getClass().getResourceAsStream("/images/user.png");
        assert userImagePath != null : "UserImage could not be loaded";
        userImage = new Image(userImagePath);

        java.io.InputStream dukeImagePath = this.getClass().getResourceAsStream("/images/duke.png");
        assert dukeImagePath != null : "DukeImage could not be loaded";
        dukeImage = new Image(dukeImagePath);

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for the UI to interact with and initializes the conversation with Duke's initial message.
     *
     * @param d The Duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(duke.init(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (duke.getExitStatus()) {
            setTimeout(Platform::exit, 1000);
        }

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Sets a timeout for executing a Runnable after a specified delay.
     *
     * @param runnable The code to be executed after the delay.
     * @param delay    The delay in milliseconds before executing the Runnable.
     */
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
}
