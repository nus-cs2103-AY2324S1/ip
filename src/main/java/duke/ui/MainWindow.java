package duke.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import duke.Duke;
import duke.commands.Command;
import duke.commands.CommandType;
import duke.exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Greets the user.
     */
    public void greet() {
        this.showDukeDialog(duke.greet());
    }

    /**
     * Shows dialog box for Duke messages.
     *
     * @param response Response message from Duke.
     */
    public void showDukeDialog(String response) {
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        this.dialogContainer.getChildren().addAll(dukeDialog);
    }

    /**
     * Shows dialog box for user messages.
     *
     * @param input Message inputted by user.
     */
    public void showUserDialog(String input) {
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        this.dialogContainer.getChildren().addAll(userDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        Command command = null;

        try {
            command = this.duke.parseInput(input);
            response = command.execute();
        } catch (DukeException e) {
            response = e.getMessage();
        }

        this.showUserDialog(input);
        this.showDukeDialog(response);
        userInput.clear();

        boolean userSaidBye = command != null && command.getCommandType() == CommandType.EXIT;
        if (userSaidBye) {
            exitDukeAfterDelay(1);
        }
    }

    private void exitDukeAfterDelay(int numSeconds) {
        this.duke.exit();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> System.exit(0), numSeconds, TimeUnit.SECONDS);
    }
}
