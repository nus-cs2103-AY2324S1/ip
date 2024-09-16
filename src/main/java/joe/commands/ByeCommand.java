package joe.commands;

import joe.Storage;
import joe.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand that marks the exit flag as true.
     */
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.saveToFile(tasks);
        return BYE_MESSAGE;
    }
}
