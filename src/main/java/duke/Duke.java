package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;

/**
 * Represents a Duke chat-bot which can store and manage tasks.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke chat-bot object.
     *
     * @param filePath The path to the file to store the tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (DukeStorageException e) {
            this.tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
