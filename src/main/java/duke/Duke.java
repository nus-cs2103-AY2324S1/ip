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

    /**
     * Constructs a Duke instance with the specified file path for data storage.
     *
     * @param filePath The file path where Duke's data is stored.
     */
    public Duke(String filePath) {
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
     * The main entry point of the Duke application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the Duke application, displaying a welcome message and processing user commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

}
