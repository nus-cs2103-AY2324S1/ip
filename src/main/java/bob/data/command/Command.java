package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;

/**
 * Represents a command that can be executed and the behavior of the execution is dependent on the command type.
 */
public abstract class Command {
    public Command() {}
    public abstract String execute(TaskList list) throws DukeException;
}
