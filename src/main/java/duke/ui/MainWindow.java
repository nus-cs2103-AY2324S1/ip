package duke.ui;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    /**
     * Initialises the MainWindow with scroll pane bound to dialog container to enable scrolling.
     */
    @FXML
    public void initialize() {
        assert this.dialogContainer != null : "Dialog container should not be null";
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.addMessages(GobbleMessage.getGobbleDialog("Hello! I'm Gobble\nWhat can I do for you?"));
    }

    /**
     * Sets the duke instance.
     *
     * @param d duke instance
     */
    public void setDuke(Duke d) {
        assert d != null : "Duke instance should not be null";
        this.duke = d;
    }

    /**
     * Listens to the input from the user and displays the appropriate message based on the command parsed.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        GobbleMessage userMessage = GobbleMessage.getUserDialog(input);
        try {
            Command command = Parser.parse(input);
            GobbleMessage message = command.execute(duke.getTasks(), duke.getStorage());
            assert message != null : "Message should not be null";

            this.addMessages(userMessage, message);

            if (input.equals("bye")) {
                System.exit(0);
            }
        } catch (DukeException e) {
            GobbleMessage error = GobbleMessage.getGobbleDialog(e.getMessage());
            this.addMessages(userMessage, error);
        }
        userInput.clear();
    }

    /**
     * Adds messages to the dialog container.
     *
     * @param messages messages to be added.
     */
    private void addMessages(GobbleMessage... messages) {
        assert messages != null : "Messages should not be null";
        assert messages.length > 0 : "Messages should not be empty";
        
        for (GobbleMessage message : messages) {
            dialogContainer.getChildren().add(message);
        }
    }
}