package moss;

import java.util.ArrayList;

/**
 * The Parser class is responsible for parsing and executing user commands for task management.
 */
public class Parser {
    private String command;
    private ArrayList<Task> things;

    private Storage storage;

    /**
     * Constructs a Parser object with the specified parameters.
     *
     * @param command The user command to be parsed and executed.
     * @param things The list of tasks to be managed.
     * @param storage The storage object for saving and loading tasks.
     */
    public Parser(String command, ArrayList<Task> things, Storage storage) {
        this.command = command;
        this.things = things;
        this.storage = storage;
    }

    /**
     * Executes the parsed user command by delegating to the TaskList class.
     *
     * @param message The user command to be executed.
     * @param things The list of tasks to be managed.
     * @param storage The storage object for saving and loading tasks.
     * @throws MossException If there's an issue with task management.
     */
    public void execute(String message, ArrayList<Task> things, Storage storage) throws MossException {
        TaskList.command(message, things, storage);
    }
}

