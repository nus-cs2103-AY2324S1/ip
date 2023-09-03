package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The Duke program is a chatbot named Beep Boop Bot that
 * executes commands to create and edit a tasklist.
 *
 * @author Inez Kok
 */
public class Duke {
    private static final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * The constructor for a Duke.
     */
    public Duke() {
        // Setting up required components
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();

        // Load tasks
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the String representation of the Duke response to a given input.
     *
     * @param input The user input given.
     * @return The String representation of the Duke response.
     */
    public String getResponse(String input) {
        if (isExit) {
            return "";
        }

        try {
            ui.resetOutput();
            Command c = Parser.parse(input, tasks.size());
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getOutput();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
