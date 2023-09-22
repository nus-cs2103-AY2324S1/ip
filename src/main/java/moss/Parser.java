package moss;

import java.util.ArrayList;

/**
 * The Parser class is responsible for parsing and executing user commands for task management.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Executes the parsed user command by delegating to the TaskList class.
     *
     * @param message The user command to be executed.
     * @param things The list of tasks to be managed.
     * @param storage The storage object for saving and loading tasks.
     * @throws MossException If there's an issue with task management.
     */
    public String execute(String message, ArrayList<Task> things, Storage storage) throws moss.MossException {
        return moss.TaskList.command(message, things, storage);
    }
}

