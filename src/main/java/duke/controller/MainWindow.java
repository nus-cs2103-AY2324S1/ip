package duke.controller;

import duke.duke.Duke;
import duke.task.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the main window of the application.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private final Image userImage = new Image(
            Objects.requireNonNull(
                    this.getClass().getResourceAsStream("/images/spiderman.jpeg")
            )
    );
    private final Image dukeImage = new Image(
            Objects.requireNonNull(
                    this.getClass().getResourceAsStream("/images/captamerica.jpeg")
            )
    );

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for this main window.
     * @param d The duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.processInputAndGetResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Displays the welcome message in the main window.
     */
    @FXML
    public void showWelcomeMessage() {
        String message = duke.ui.showWelcomeMessage();
        assert message.equals("Hello! I'm Victor\n"
                + "What can I do for you?\n----------\n") : "Welcome message is wrong";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(message, dukeImage)
        );
    }

    /**
     * Loads tasks from text file.
     */
    @FXML
    public void loadTasksFromFile() {
        try {
            List<Task> list = duke.getTaskList().getList();
            assert list.equals(new ArrayList<Task>(100))
                    : "List of tasks should be empty";
            duke.storage.loadTasksFromFile(list);
        } catch (Exception e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        }
    }
}
