package duke;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Duke class that deals with the main logic of the program.
 */
public class Duke {

    private static final String DUKE_FILEPATH = "./src/main/data/duke.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke. Creates a new Ui, Storage and TaskList.
     * If there is an error loading the file, a new TaskList is created.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DUKE_FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns a list of tasks.
     *
     * @return list of tasks.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns the storage.
     *
     * @return storage.
     */
    public Storage getStorage() {
        return storage;
    }
}
