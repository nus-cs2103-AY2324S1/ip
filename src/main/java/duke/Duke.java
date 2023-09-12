package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * The main class for the Duke chatbot.
 */
public class Duke {
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        TaskList tasks;
        TextUi ui = new TextUi();
        Storage storage = new Storage();
        Parser parser = new Parser();

        ui.showWelcomeMessage();

        try {
            tasks = storage.load();
            if (tasks.size() == 0) {
                ui.showMessage(String.format("No stored tasks found from %s", Storage.DEFAULT_STORAGE_PATH),
                        "Starting from an empty task list.");
                tasks = new TaskList();
            } else {
                ui.showMessage(String.format("Tasks loaded from %s", Storage.DEFAULT_STORAGE_PATH));
            }
        } catch (IOException e) {
            ui.showMessage(String.format("Error loading tasks from %s", Storage.DEFAULT_STORAGE_PATH),
                    "Starting from an empty task list.");
            tasks = new TaskList();
        }

        Command command;
        do {
            String input = ui.getUserCommand();
            command = parser.parseCommand(input);
            command.setData(tasks);
            String[] response = command.execute();
            ui.showMessage(response);

            try {
                storage.save(tasks);
            } catch (IOException e) {
                ui.showMessage("Error saving tasks");
            }
        } while (!command.isBye());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
