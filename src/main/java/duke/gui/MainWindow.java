package duke.gui;

import java.util.concurrent.CompletableFuture;

import duke.Duke;
import duke.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label bobLabel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaGigachad.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaBob.png"));

    /**
     * Initializes the chatbot GUI interface.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialises Duke in the GUI and displays a welcome message in a dialog box.
     * @param d The Duke instance for the GUI to use.
     */
    public void setDuke(Duke d) {
        duke = d;

        // print a welcome message
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcomeMessage(), dukeImage, false)
        );
    }

    /**
     * Creates a new dialog box with the exit message, waits 500ms, then closes the GUI program.
     * @param input The input message that triggers this exit handler.
     */
    private void handleExit(String input) {
        assert input.startsWith("bye") : input;
        // print an exit message, waits 500ms, then exits the program.
        CompletableFuture.completedFuture(dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(duke.getExitMessage(), dukeImage, false)
        )).thenRunAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Platform.exit();
            }
        }).thenRunAsync(Platform::exit);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. If there is an error in input, Duke's reply will
     * be in dark red.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";
        boolean isError = false;

        // try to get a response, else print an error message to be used for Duke's output in the GUI
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            response = e.toString();
            isError = true;
        }

        userInput.clear();

        // there is a response; do not exit the GUI program
        if (response != null) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage, isError)
            );
        } else {
            handleExit(input);
        }
    }
}
