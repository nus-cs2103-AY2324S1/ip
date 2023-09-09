package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Main class that drives the Duke chatbot.
 */
public class Duke {

    public Storage storage;
    protected TaskList tasks;

    /**
     * Creates a Duke chatbot that initialises new Storage and TaskList objects.
     * @param filePath File path of the local text database.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.readFromDatabase(), this);
        } catch (DukeException e) {
            this.tasks = new TaskList(new ArrayList<Task>(), this);
        }
    }
}
