package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.exceptions.IncompleteDescriptionException;
import duke.exceptions.UnknownCommandException;
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

    private TaskList taskList;
    private Storage storage;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.taskList = new TaskList();
        this.storage = new Storage("data/duke.txt");
        this.storage.loadTaskFile(taskList);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.opening, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = getResponse(input, this.taskList, this.storage);
        assert !response.isBlank() : "fatal error occurred";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        assert userInput.getText().isBlank() : "something went wrong";
        if (response.equals(Ui.closing)) {
            System.exit(0);
        }
    }

    private String getResponse(String input, TaskList taskList, Storage storage) {
        try {
            Command cmd = Parser.cmdFromString(input);
            return cmd.execute(taskList, storage);
        } catch (UnknownCommandException | IncompleteDescriptionException ex) {
            return ex.toString();
        }

    }
}
