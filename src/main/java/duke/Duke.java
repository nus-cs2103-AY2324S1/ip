package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Entry point of the Duke application for text-ui based application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private final Storage storage;
    private final Tasks tasks;
    private final Ui ui;

    /**
     * Public constructor for Duke.
     *
     * <p>Initialises data structures used by the application.</p>
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        tasks = storage.load();
    }

    /**
     * Handler method for GUI application to respond to user input
     *
     * @param input the input passed by user from the GUI
     * @return string response for the user input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, false);

            // Empty or unknown command
            if (c == null) {
                return ui.showUnknownCommand();
            }

            // Check if is duke.commands.ExitCommand
            if (c.isExit()) {
                return "";
            }

            // Execute command
            return c.execute(this.tasks, this.ui, this.storage, false);
        } catch (DukeException ex) {
            return ui.showError(ex.getMessage());
        }
    }
}
