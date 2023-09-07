package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The main class for the Duke application, a simple task management system.
 * Duke allows users to manage their tasks through a command-line interface.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke instance.
     *
     * @throws DukeException If there is an issue initializing Duke or loading tasks from the data file.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage("data/duke.txt");
            this.tasks = storage.loadIntoList(new TaskList());
        } catch (DukeException e) {
            ui.printException(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printException(e.getMessage());
        }
    }

}
