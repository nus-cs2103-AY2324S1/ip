package joe.commands;

import joe.Storage;
import joe.TaskList;

/**
 * Represents a command for handling invalid user inputs.
 */
public class InvalidCommand extends Command {
    private final String msg;

    /**
     * Constructs an InvalidCommand with an error message.
     *
     * @param msg The error message to be displayed.
     */
    public InvalidCommand(String msg) {
        this.msg = msg;
    }

    /**
     * Executes the command to display an error message.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return msg;
    }
}
