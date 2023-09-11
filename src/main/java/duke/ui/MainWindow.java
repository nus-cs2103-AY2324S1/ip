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
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.addMessages(GobbleMessage.getGobbleDialog("Hello! I'm Gobble\nWhat can I do for you?"));
    }

    /**
     * Sets the duke instance.
     *
     * @param d Duke instance
     */
    public void setDuke(Duke d) {
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
            if (command.isExit()) {
                System.exit(0);
            }
            GobbleMessage message = command.execute(duke.getTasks(), duke.getStorage());
            this.addMessages(userMessage, message);
        } catch (DukeException e) {
            GobbleMessage error = GobbleMessage.getGobbleDialog(e.getMessage());
            this.addMessages(userMessage, error);
        }
        userInput.clear();
    }

    /**
     * Adds messages to the dialog container.
     *
     * @param messages Messages to be added.
     */
    private void addMessages(GobbleMessage... messages) {
        for (GobbleMessage message : messages) {
            dialogContainer.getChildren().add(message);
        }
    }
}