package bob.data.command;

import bob.data.task.TaskList;

/**
 * Represents a command that was wrongly input.
 */
public class InvalidCommand extends Command {
    private String error;

    /**
     * Creates a new InvalidCommand that is invalid due to the specified error.
     * @param error The error which caused this InvalidCommand to be created.
     */
    public InvalidCommand(String error) {
        this.error = error;
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    public String execute(TaskList list) {
        return "Invalid command:" + error;
    }
}
