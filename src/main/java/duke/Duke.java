package duke;

import duke.commands.Command;
import duke.commands.CommandException;
import duke.commands.CommandResult;
import duke.storage.Storage;
import duke.storage.StorageException;

/**
 * Duke is a task management tool.
 */
public class Duke {
    private static final String NAME = "Jimmy";
    private static final String TASKS_CACHE_PATH = ".duke-cache";
    private final Storage storage;
    private TaskList tasks;
    private boolean shouldTerminate;


    /**
     * Constructor for an instance of Duke.
     */
    public Duke() {
        this.storage = new Storage(TASKS_CACHE_PATH);
        this.tasks = new TaskList();
        this.shouldTerminate = false;
    }

    public void loadTasks() throws StorageException {
        tasks = storage.load();
    }

    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    public String getGreetings() {
        return String.format("Hello, I'm %s!", NAME);
    }

    /**
     * Generates a response from Duke based on the input.
     *
     * @param input The input from the user.
     * @return A string containing the response from duke.
     */
    public String getResponse(String input) {
        try {
            Command command = Command.parse(input);
            assert command != null;

            if (command.shouldExit()) {
                shouldTerminate = true;
            }

            CommandResult result = command.run(tasks);

            if (result.isTaskListDirty()) {
                storage.save(tasks);
            }

            return String.join("\n", result.getResponse());
        } catch (CommandException e) {
            return e.getMessage();
        }
    }
}
