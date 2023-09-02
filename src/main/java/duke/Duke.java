package duke;

import duke.command.Command;
import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.parser.Parser;
import duke.task.TaskList;

/**
 * Duke is a simple task management application that allows users to add, list, and manage tasks. It provides a
 * command-line interface for interacting with tasks.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    private boolean hasExited;

    /**
     * Constructs a Duke instance with the specified file path for data storage.
     */
    public Duke() {
        this.ui = new UI();
        this.storage = new Storage("data", "duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Shows the welcome message for Duke.
     */
    public String init() {
        ui.showWelcome();
        return ui.getStatus();
    }

    public boolean getExitStatus() {
        return hasExited;
    }

    /**
     * Runs the Duke application, displaying a welcome message and processing user commands until the user exits.
     */
    public void run(String input) {
        try {
            ui.showLine();
            Command c = Parser.parse(input);
            hasExited = c.isExit();
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e);
        } finally {
            ui.showLine();
        }
    }

    /**
     * You should have your own function to generate a response to user input. Replace this stub with your completed
     * method.
     */
    public String getResponse(String input) {
        run(input);
        return ui.getStatus();
    }
}
